package pl.mankevich.githubrepositorybrowserum.data.remote

import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val HEADER_TOKEN_NAME = "Authorization"
private const val TOKEN_TYPE = "Bearer"
private const val GIT_TOKEN_PART_1 = "ghp_"
private const val GIT_TOKEN_PART_2 = "4cidk2FUs7DG5j8wQdAhP8rXPpbdf31DeuDp"

class  GitApolloInterceptor @Inject constructor() : ApolloInterceptor {

    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain
    ): Flow<ApolloResponse<D>> {
        val gitRequest = request.newBuilder().addHttpHeader(HEADER_TOKEN_NAME, "$TOKEN_TYPE $GIT_TOKEN_PART_1$GIT_TOKEN_PART_2").build()
        return chain.proceed(gitRequest)
    }
}