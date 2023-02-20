package pl.mankevich.githubrepositorybrowserum.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query
import javax.inject.Inject

class GitApi @Inject constructor(
    private val apolloClient: ApolloClient
) {

    suspend fun <D: Query.Data, Q: Query<D>> execute(query: Q): ApolloResponse<D> {
        return apolloClient.query(query).execute()
    }
}