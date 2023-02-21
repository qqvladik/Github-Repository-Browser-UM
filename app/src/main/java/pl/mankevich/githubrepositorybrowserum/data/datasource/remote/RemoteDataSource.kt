package pl.mankevich.githubrepositorybrowserum.data.datasource.remote

import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.response.GitRepsResponse
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepListRequest
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest

interface RemoteDataSource {

    suspend fun fetchGitRepDetail(request: GitRepRequest): GitRepDetailDto

    suspend fun fetchGitRepsResponse(request: GitRepListRequest): GitRepsResponse
}