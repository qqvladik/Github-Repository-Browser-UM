package pl.mankevich.githubrepositorybrowserum.data.remote

import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val HEADER_NAME = "Authorization"
const val BEARER = "Bearer"
const val GIT_TOKEN = "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"

class  GitApolloInterceptor @Inject constructor() : ApolloInterceptor {

    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain
    ): Flow<ApolloResponse<D>> {
        val gitRequest = request.newBuilder().addHttpHeader(HEADER_NAME, "$BEARER $GIT_TOKEN").build()
        return chain.proceed(gitRequest)
    }
}