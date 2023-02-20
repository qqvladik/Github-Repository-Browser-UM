package pl.mankevich.githubrepositorybrowserum.domain.usecase

import androidx.paging.Pager
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import pl.mankevich.githubrepositorybrowserum.domain.repository.Repository
import javax.inject.Inject

class FetchRepListUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(ownerLogin: String): Pager<String, GitRepSimpleDto> =
        repository.fetchGitRepList(ownerLogin)
}