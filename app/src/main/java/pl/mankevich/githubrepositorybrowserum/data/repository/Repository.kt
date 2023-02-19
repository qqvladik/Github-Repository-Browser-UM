package pl.mankevich.githubrepositorybrowserum.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import pl.mankevich.githubrepositorybrowserum.data.datasource.remote.impl.RemoteDataSourceImpl
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepListRequest
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) { //TODO добавить интерфейс Repository в слой domain

    fun fetchGitRepList(request: GitRepListRequest) = Pager(PagingConfig(request.pageSize)) {
        GitRepListPagingSource(remoteDataSourceImpl, request)
    }.flow

    suspend fun fetchGitRepDetail(request: GitRepRequest): GitRepDetailDto {
        return remoteDataSourceImpl.fetchGitRepDetailDto(request)
    }
}