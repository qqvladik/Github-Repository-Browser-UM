package pl.mankevich.githubrepositorybrowserum.domain.usecase

import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest
import pl.mankevich.githubrepositorybrowserum.domain.repository.Repository
import javax.inject.Inject

class FetchRepDetailUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(request: GitRepRequest): GitRepDetailDto = repository.fetchGitRepDetail(request)
}