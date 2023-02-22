package pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.error
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.execute
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.loadExecutor
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.success

abstract class MvvmViewModel : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    open fun handleError(error: Throwable) {}

    open fun startLoading() {}

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    protected suspend fun <T> execute(
        call: suspend () -> T,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        loadExecutor {
            execute {
                startLoading()
                call.invoke()
            }
            success { result ->
                completionHandler.invoke(result)
            }
            error { error ->
                handleError(error)
            }
        }
    }

}