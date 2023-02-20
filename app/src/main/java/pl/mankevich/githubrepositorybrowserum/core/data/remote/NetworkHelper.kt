package pl.mankevich.githubrepositorybrowserum.core.data.remote

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query

interface NetworkHelper {
    /**
     * @param DTO is a returning response type (DTO)
     * @param SERVICE_DATA is a response from service which contains data [DTO] and status code
     * @param apiCall is a suspend lambda function, which returns some data from remote source
     */
    suspend fun <SERVICE_DATA : Query.Data> apiExecute(apiCall: suspend () -> ApolloResponse<SERVICE_DATA>): SERVICE_DATA
}