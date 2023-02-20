package pl.mankevich.githubrepositorybrowserum.core.presentation.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class MvvmViewModel : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    open fun handleError(exception: Throwable) {}

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

fun <T> MvvmViewModel.loadExecutor(loadExecutorBuilder: LoadExecutorBuilder<T>.() -> Unit) {
    viewModelScope.loadOperation(loadExecutorBuilder)
}

fun <T> CoroutineScope.loadOperation(loadExecutorBuilder: LoadExecutorBuilder<T>.() -> Unit) {
    this.launch {
        val builder = LoadExecutorBuilder<T>().apply { loadExecutorBuilder() }
        try {
            builder.builderStartLambda?.invoke()

            val result = builder.builderApiCall?.invoke(this)!!

            builder.builderSuccess.invoke(result)


        } catch (e: Throwable) {
            builder.builderErrorLambda?.invoke(this, e)
        } finally {
            builder.builderFinishLambda?.invoke()
        }
    }
}