package pl.mankevich.githubrepositorybrowserum.data.mapper

import pl.mankevich.githubrepositorybrowserum.GetRepByNameAndOwnerLoginQuery
import pl.mankevich.githubrepositorybrowserum.core.data.mapper.Mapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest
import javax.inject.Inject

class GitRepRequestToQueryMapper @Inject constructor() :
    Mapper<GitRepRequest, GetRepByNameAndOwnerLoginQuery> {

    override fun map(input: GitRepRequest): GetRepByNameAndOwnerLoginQuery {
        return GetRepByNameAndOwnerLoginQuery(
            name = input.name,
            ownerLogin = input.ownerLogin
        )
    }
}