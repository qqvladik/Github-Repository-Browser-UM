package pl.mankevich.githubrepositorybrowserum.presentation.list

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.mvi.BaseViewState
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.mvi.MviViewModel
import pl.mankevich.githubrepositorybrowserum.domain.usecase.FetchRepListUseCase
import javax.inject.Inject

@HiltViewModel
class GitRepListViewModel @Inject constructor(
    private val fetchRepListUseCase: FetchRepListUseCase
) : MviViewModel<BaseViewState<GitRepListViewState>, GitRepListEvent>() {

        override fun onTriggerEvent(eventType: GitRepListEvent) {
            when (eventType) {
                is GitRepListEvent.LoadList -> onLoadList(eventType.ownerLogin)
            }
        }

        private fun onLoadList(ownerLogin: String) = safeLaunch {
            execute(
                call = {
                    fetchRepListUseCase(ownerLogin)
                },
                completionHandler = { pagedData ->
                    setState(BaseViewState.Data(GitRepListViewState(pagedData = pagedData)))
                }
            )
        }
}