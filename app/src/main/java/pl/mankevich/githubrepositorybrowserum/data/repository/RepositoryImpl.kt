package pl.mankevich.githubrepositorybrowserum.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.flow.Flow
import pl.mankevich.githubrepositorybrowserum.core.utils.PAGE_SIZE
import pl.mankevich.githubrepositorybrowserum.data.datasource.remote.impl.RemoteDataSourceImpl
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest
import pl.mankevich.githubrepositorybrowserum.domain.repository.Repository
import javax.inject.Inject

@BoundTo(supertype = Repository::class, component = SingletonComponent::class)
class RepositoryImpl @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) : Repository {

    override fun fetchGitRepList(ownerLogin: String): Flow<PagingData<GitRepSimpleDto>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            GitRepListPagingSource(remoteDataSourceImpl, ownerLogin)
        }.flow
    }

    override suspend fun fetchGitRepDetail(request: GitRepRequest): GitRepDetailDto {
        return remoteDataSourceImpl.fetchGitRepDetail(request)
    }
}