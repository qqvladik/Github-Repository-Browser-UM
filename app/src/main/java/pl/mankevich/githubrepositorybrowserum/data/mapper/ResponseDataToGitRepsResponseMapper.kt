package pl.mankevich.githubrepositorybrowserum.data.mapper

import pl.mankevich.githubrepositorybrowserum.GetRepsByOwnerLoginQuery
import pl.mankevich.githubrepositorybrowserum.core.data.mapper.Mapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepsResponseDto
import javax.inject.Inject

class ResponseDataToGitRepsResponseMapper @Inject constructor() :
    Mapper<GetRepsByOwnerLoginQuery.Data, GitRepsResponseDto> {

    override fun map(input: GetRepsByOwnerLoginQuery.Data): GitRepsResponseDto {
        return input.user?.repositories?.let { reps ->
            val gitRepSimpleList = reps.edges?.map { edge ->
                GitRepSimpleDto(edge?.node?.repositorySimple?.name)
            } ?: throw Exception("Response for GetRepsByOwnerLoginQuery doesn't contain repositories data!")
            reps.pageInfo.let { pageInfo ->
                val startCursor = pageInfo.startCursor
                val hasPreviousPage = pageInfo.hasPreviousPage
                val endCursor = pageInfo.endCursor
                val hasNextPage = pageInfo.hasNextPage
                GitRepsResponseDto(
                    gitRepSimpleList = gitRepSimpleList,
                    startCursor = startCursor,
                    hasPreviousPage = hasPreviousPage,
                    endCursor = endCursor,
                    hasNextPage = hasNextPage
                )
            }
        } ?: throw Exception("Response for GetRepsByOwnerLoginQuery doesn't contain body!")


    }
}