package pl.mankevich.githubrepositorybrowserum.presentation.detail

import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepDetailDto

data class GitRepDetailViewState(
    val gitRepDetailDto: GitRepDetailDto? = null
)

sealed class GitRepDetailEvent {

    data class LoadDetail(
        val name: String,
        val ownerLogin: String
    ) : GitRepDetailEvent()
}