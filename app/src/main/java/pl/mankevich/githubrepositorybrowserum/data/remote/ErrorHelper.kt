package pl.mankevich.githubrepositorybrowserum.data.remote

import android.content.Context
import com.apollographql.apollo3.exception.ApolloException
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.extractInt
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.isNetworkAvailable
import pl.mankevich.githubrepositorybrowserum.data.remote.error.ErrorEntity
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * ErrorHandler interface in domain layer, for propagating errors from data to domain layers
 */
interface ErrorHelper {
    fun getErrorByThrowable(throwable: Throwable): ErrorEntity
}

@BoundTo(supertype = ErrorHelper::class, component = SingletonComponent::class)
class ErrorHelperImpl @Inject constructor(private val context: Context) : ErrorHelper {
    override fun getErrorByThrowable(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is ApolloException -> {
                throwable.message.let { message ->
                    message?.let {
                        if (message.contains("Http request failed with status code")) {
                            when (message.extractInt()) {
                                HttpURLConnection.HTTP_UNAUTHORIZED -> throw ErrorEntity.UnauthorizedError(message)
                                HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFoundError(message)
                                else -> ErrorEntity.UnknownError(message)
                            }
                        } else {
                            ErrorEntity.UnknownError(message)
                        }
                    } ?: ErrorEntity.UnknownError(message)
                }
            }
            is SocketTimeoutException -> {
                ErrorEntity.ServerTimeoutError()
            }
            is IOException -> {
                if (isInternetAvailable()) {
                    ErrorEntity.InternalServerError(throwable.message)
                } else {
                    ErrorEntity.NoConnectionError()
                }
            }
            else -> ErrorEntity.UnknownError(throwable.message)
        }
    }

    private fun isInternetAvailable(): Boolean = context.isNetworkAvailable()
}
