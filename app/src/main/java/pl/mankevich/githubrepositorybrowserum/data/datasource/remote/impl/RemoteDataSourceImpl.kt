package pl.mankevich.githubrepositorybrowserum.data.datasource.remote.impl

import com.apollographql.apollo3.ApolloClient
import pl.mankevich.githubrepositorybrowserum.data.datasource.remote.RemoteDataSource
import pl.mankevich.githubrepositorybrowserum.data.mapper.GitRepRequestToQueryMapper
import pl.mankevich.githubrepositorybrowserum.data.mapper.GitRepListRequestToQueryMapper
import pl.mankevich.githubrepositorybrowserum.data.mapper.ResponseDataToGitRepMapper
import pl.mankevich.githubrepositorybrowserum.data.mapper.ResponseDataToGitRepListMapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepListRequest
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val gitRepRequestToQueryMapper: GitRepRequestToQueryMapper,
    private val gitRepListRequestToQueryMapper: GitRepListRequestToQueryMapper,
    private val responseDataToGitRepMapper: ResponseDataToGitRepMapper,
    private val responseDataToGitRepListMapper: ResponseDataToGitRepListMapper
) : RemoteDataSource {

    suspend fun fetchGitRepDetailDto(request: GitRepRequest): GitRepDetailDto {
        return responseDataToGitRepMapper.map(
            apolloClient.query(
                gitRepRequestToQueryMapper.map(request)
            ).execute().dataAssertNoErrors
        )
    }

    suspend fun fetchGitRepSimpleDtoList(request: GitRepListRequest): List<GitRepSimpleDto> {
        return responseDataToGitRepListMapper.map(
            apolloClient.query(
                gitRepListRequestToQueryMapper.map(request)
            ).execute().dataAssertNoErrors
        )
    }
}