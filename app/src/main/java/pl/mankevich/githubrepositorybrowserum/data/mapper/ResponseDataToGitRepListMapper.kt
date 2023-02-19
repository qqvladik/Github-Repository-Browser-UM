package pl.mankevich.githubrepositorybrowserum.data.mapper

import pl.mankevich.githubrepositorybrowserum.GetRepsByOwnerLoginQuery
import pl.mankevich.githubrepositorybrowserum.core.mapper.Mapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import javax.inject.Inject

class ResponseDataToGitRepListMapper @Inject constructor() :
    Mapper<GetRepsByOwnerLoginQuery.Data, List<GitRepSimpleDto>> {

    override fun map(input: GetRepsByOwnerLoginQuery.Data): List<GitRepSimpleDto> {
        return input.user?.repositories?.edges?.map { edge ->
            GitRepSimpleDto(edge?.node?.repositorySimple?.name)
        }?:throw Exception("Response for GetRepsByOwnerLoginQuery doesn't contain repository data!")
    }
}