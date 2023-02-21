package pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.mvvm.MvvmViewModel

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