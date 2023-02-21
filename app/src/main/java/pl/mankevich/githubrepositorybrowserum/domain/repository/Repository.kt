package pl.mankevich.githubrepositorybrowserum.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest

interface Repository {

    fun fetchGitRepList(ownerLogin: String): Flow<PagingData<GitRepSimpleDto>>

    suspend fun fetchGitRepDetail(request: GitRepRequest): GitRepDetailDto
}