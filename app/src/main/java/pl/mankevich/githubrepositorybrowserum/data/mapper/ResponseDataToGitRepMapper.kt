package pl.mankevich.githubrepositorybrowserum.data.mapper

import pl.mankevich.githubrepositorybrowserum.GetRepByNameAndOwnerLoginQuery
import pl.mankevich.githubrepositorybrowserum.core.mapper.Mapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import javax.inject.Inject

class ResponseDataToGitRepMapper @Inject constructor() :
    Mapper<GetRepByNameAndOwnerLoginQuery.Data, GitRepDetailDto?> {

    override fun map(input: GetRepByNameAndOwnerLoginQuery.Data): GitRepDetailDto {
        return input.repository?.repositoryDetail?.let { rep ->
            GitRepDetailDto(
                ownerLogin = rep.owner.login,
                name = rep.name,
                description = rep.name,
                commitsNumber = rep.defaultBranchRef?.target?.onCommit?.history?.totalCount,
                issuesNumber = rep.issues.totalCount
            )
        } ?: throw Exception("Response for GetRepByNameAndOwnerLoginQuery doesn't contain repository data!")//TODO improve
    }
}