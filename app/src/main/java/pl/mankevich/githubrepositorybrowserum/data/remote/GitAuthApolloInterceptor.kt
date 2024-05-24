package pl.mankevich.githubrepositorybrowserum.data.remote

import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import kotlinx.coroutines.flow.Flow
import pl.mankevich.githubrepositorybrowserum.BuildConfig
import javax.inject.Inject

private const val HEADER_TOKEN_NAME = "Authorization"
private const val TOKEN_TYPE = "Bearer"
private const val GIT_TOKEN = BuildConfig.GIT_TOKEN

class  GitApolloInterceptor @Inject constructor() : ApolloInterceptor {

    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain
    ): Flow<ApolloResponse<D>> {
        val gitRequest = request.newBuilder().addHttpHeader(
            HEADER_TOKEN_NAME, "$TOKEN_TYPE $GIT_TOKEN"
        ).build()
        return chain.proceed(gitRequest)
    }
}