package pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.mvvm.MvvmViewModel

abstract class MviViewModel<STATE : BaseViewState<*>, EVENT> : MvvmViewModel() {

    private val _uiState = MutableStateFlow<BaseViewState<*>>(BaseViewState.Empty)
    val uiState = _uiState.asStateFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.emit(state)
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.value = BaseViewState.Loading
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        _uiState.value = BaseViewState.Error(error)
    }
}