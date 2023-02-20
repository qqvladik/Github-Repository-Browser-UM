package pl.mankevich.githubrepositorybrowserum.data.remote

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import pl.mankevich.githubrepositorybrowserum.core.data.remote.NetworkHelper
import pl.mankevich.githubrepositorybrowserum.data.remote.error.ErrorEntity
import javax.inject.Inject

@BoundTo(supertype = NetworkHelper::class, component = SingletonComponent::class)
class NetworkHelperImpl @Inject constructor(private val errorHelper: ErrorHelper) : NetworkHelper {

    override suspend fun <SERVICE_DATA : Query.Data> apiExecute(
        apiCall: suspend () -> ApolloResponse<SERVICE_DATA>
    ): SERVICE_DATA {
        return try {
            val response = apiCall.invoke()

            if (!response.hasErrors()) {
                response.data ?: throw  ErrorEntity.ServerReturnNoDataError("The server did not return any data")
            } else {
                throw ErrorEntity.ResponseError(response.errors!!.first().message)
            }
        } catch (throwable: Throwable) {
            throw errorHelper.getErrorByThrowable(throwable)
        }
    }
}
