package pl.mankevich.githubrepositorybrowserum.data.mapper

import pl.mankevich.githubrepositorybrowserum.GetRepsByOwnerLoginQuery
import pl.mankevich.githubrepositorybrowserum.core.data.mapper.Mapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.response.GitRepsResponse
import pl.mankevich.githubrepositorybrowserum.data.model.remote.response.PageInfo
import pl.mankevich.githubrepositorybrowserum.data.remote.ErrorEntity
import javax.inject.Inject

class ResponseDataToGitRepsResponseMapper @Inject constructor() :
    Mapper<GetRepsByOwnerLoginQuery.Data, GitRepsResponse> {

    override fun map(input: GetRepsByOwnerLoginQuery.Data): GitRepsResponse {
        return input.user?.repositories?.let { reps ->
            val totalCount = reps.totalCount
            val gitRepSimpleList = reps.edges?.map { edge ->
                GitRepSimpleDto(edge?.node?.repositorySimple?.name)
            } ?: throw Exception("Response for GetRepsByOwnerLoginQuery doesn't contain repositories data!")
            reps.pageInfo.let { pageInfo ->
                val startCursor = pageInfo.startCursor
                val hasPreviousPage = pageInfo.hasPreviousPage
                val endCursor = pageInfo.endCursor
                val hasNextPage = pageInfo.hasNextPage
                GitRepsResponse(
                    totalCount = totalCount,
                    gitRepSimpleList = gitRepSimpleList,
                    pageInfo = PageInfo(
                        startCursor = startCursor,
                        hasPreviousPage = hasPreviousPage,
                        endCursor = endCursor,
                        hasNextPage = hasNextPage
                    )
                )
            }
        } ?: throw ErrorEntity.ServerReturnNoDataError("The server did not return any data for GetRepsByOwnerLoginQuery")


    }
}