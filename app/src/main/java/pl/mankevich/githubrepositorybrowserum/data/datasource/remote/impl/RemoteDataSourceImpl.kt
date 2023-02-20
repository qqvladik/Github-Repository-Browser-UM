package pl.mankevich.githubrepositorybrowserum.data.datasource.remote.impl

import com.apollographql.apollo3.ApolloClient
import pl.mankevich.githubrepositorybrowserum.core.data.remote.NetworkHelper
import pl.mankevich.githubrepositorybrowserum.data.datasource.remote.RemoteDataSource
import pl.mankevich.githubrepositorybrowserum.data.mapper.GitRepListRequestToQueryMapper
import pl.mankevich.githubrepositorybrowserum.data.mapper.GitRepRequestToQueryMapper
import pl.mankevich.githubrepositorybrowserum.data.mapper.ResponseDataToGitRepMapper
import pl.mankevich.githubrepositorybrowserum.data.mapper.ResponseDataToGitRepsResponseMapper
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepsResponseDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepListRequest
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val gitRepRequestToQueryMapper: GitRepRequestToQueryMapper,
    private val gitRepListRequestToQueryMapper: GitRepListRequestToQueryMapper,
    private val responseDataToGitRepMapper: ResponseDataToGitRepMapper,
    private val responseDataToGitRepsResponseMapper: ResponseDataToGitRepsResponseMapper,
    private val networkHelper: NetworkHelper
) : RemoteDataSource {

    override suspend fun fetchGitRepDetail(request: GitRepRequest): GitRepDetailDto {
        return responseDataToGitRepMapper.map(
            networkHelper.apiExecute {
                apolloClient.query(
                    gitRepRequestToQueryMapper.map(request)
                ).execute()
            }
        )
    }

    override suspend fun fetchGitRepsResponse(request: GitRepListRequest): GitRepsResponseDto {
        return responseDataToGitRepsResponseMapper.map(
            networkHelper.apiExecute {
                apolloClient.query(
                    gitRepListRequestToQueryMapper.map(request)
                ).execute()
            }
        )
    }
}