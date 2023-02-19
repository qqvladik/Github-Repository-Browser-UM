package pl.mankevich.githubrepositorybrowserum.data.mapper

import com.apollographql.apollo3.api.Optional
import pl.mankevich.githubrepositorybrowserum.GetRepsByOwnerLoginQuery
import pl.mankevich.githubrepositorybrowserum.core.mapper.Mapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepListRequest
import javax.inject.Inject

class GitRepListRequestToQueryMapper @Inject constructor() :
    Mapper<GitRepListRequest, GetRepsByOwnerLoginQuery> {

    override fun map(input: GitRepListRequest): GetRepsByOwnerLoginQuery {
        return GetRepsByOwnerLoginQuery(
            ownerLogin = input.ownerLogin,
            pageSize = 10,//TODO сделать правильно
            cursor = Optional.absent()
        )
    }
}