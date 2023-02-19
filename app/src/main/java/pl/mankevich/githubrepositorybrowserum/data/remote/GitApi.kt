package pl.mankevich.githubrepositorybrowserum.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.Query
import pl.mankevich.githubrepositorybrowserum.GetRepsByOwnerLoginQuery
import javax.inject.Inject

class GitApi @Inject constructor(
    private val apolloClient: ApolloClient
) {//TODO возможно его вообще убрать нужно

    suspend fun <D: Query.Data, Q: Query<D>> execute(query: Q): D? {
        return apolloClient.query(query).execute().data
    }

    suspend fun fetchGitRepSimpleListResponseData(ownerLogin: String): GetRepsByOwnerLoginQuery.Data? {
        return apolloClient.query(
            GetRepsByOwnerLoginQuery(
                ownerLogin = ownerLogin,
                pageSize = 10, //TODO сделать правильно
                cursor = Optional.absent()
            )
        ).execute().data
    }
}