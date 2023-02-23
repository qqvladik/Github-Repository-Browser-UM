package pl.mankevich.githubrepositorybrowserum.data.remote

import com.apollographql.apollo3.exception.ApolloException
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.extractInt
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
class ErrorHelperImpl @Inject constructor() : ErrorHelper {
    override fun getErrorByThrowable(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is ErrorEntity -> throwable
            is ApolloException -> {
                throwable.message.let { message ->
                    message?.let {
                        if (message.contains("Http request failed with status code")) {
                            when (message.extractInt()) {
                                HttpURLConnection.HTTP_UNAUTHORIZED -> throw ErrorEntity.UnauthorizedError(
                                    "Unauthorized error 401. May be your github token is invalid"
                                )
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
            is IOException -> ErrorEntity.InternalServerError(throwable.message)
            else -> ErrorEntity.UnknownError(throwable.message)
        }
    }
}
