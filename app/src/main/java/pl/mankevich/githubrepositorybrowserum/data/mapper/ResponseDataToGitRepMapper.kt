package pl.mankevich.githubrepositorybrowserum.data.mapper

import pl.mankevich.githubrepositorybrowserum.GetRepByNameAndOwnerLoginQuery
import pl.mankevich.githubrepositorybrowserum.core.data.mapper.Mapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import pl.mankevich.githubrepositorybrowserum.data.remote.ErrorEntity
import javax.inject.Inject

class ResponseDataToGitRepMapper @Inject constructor() :
    Mapper<GetRepByNameAndOwnerLoginQuery.Data, GitRepDetailDto?> {

    override fun map(input: GetRepByNameAndOwnerLoginQuery.Data): GitRepDetailDto {
        return input.repository?.repositoryDetail?.let { rep ->
            GitRepDetailDto(
                ownerLogin = rep.owner.login,
                name = rep.name,
                description = rep.description,
                commitsNumber = rep.defaultBranchRef?.target?.onCommit?.history?.totalCount,
                issuesNumber = rep.issues.totalCount
            )
        }
            ?: throw ErrorEntity.ServerReturnNoDataError("The server did not return any data for GetRepsByOwnerLoginQuery")
    }
}