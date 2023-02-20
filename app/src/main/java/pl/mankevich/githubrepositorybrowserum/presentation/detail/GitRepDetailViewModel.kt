package pl.mankevich.githubrepositorybrowserum.presentation.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mankevich.githubrepositorybrowserum.core.presentation.mvi.BaseViewState
import pl.mankevich.githubrepositorybrowserum.core.presentation.mvi.MviViewModel
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepRequest
import pl.mankevich.githubrepositorybrowserum.domain.usecase.FetchRepDetailUseCase
import javax.inject.Inject

@HiltViewModel
class GitRepDetailViewModel @Inject constructor(
    private val fetchRepDetailUseCase: FetchRepDetailUseCase
) : MviViewModel<BaseViewState<GitRepDetailViewState>, GitRepDetailEvent>() {

    override fun onTriggerEvent(eventType: GitRepDetailEvent) {
        when (eventType) {
            is GitRepDetailEvent.LoadDetail -> onLoadDetail(eventType.name, eventType.ownerLogin)
        }
    }

    private fun onLoadDetail(name: String, ownerLogin: String) = safeLaunch {
        val request = GitRepRequest(name, ownerLogin)
        execute(
            call = {
                fetchRepDetailUseCase(request)
            },
            completionHandler = { dto ->
                setState(BaseViewState.Data(GitRepDetailViewState(gitRepDetailDto = dto)))
            }
        )
    }

}