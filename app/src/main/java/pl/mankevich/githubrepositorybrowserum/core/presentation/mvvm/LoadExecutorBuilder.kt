package pl.mankevich.githubrepositorybrowserum.core.presentation.mvvm

import kotlinx.coroutines.CoroutineScope

class LoadExecutorBuilder<T> {
    internal var builderApiCall: (suspend CoroutineScope.() -> T)? = null
    internal var builderStartLambda: (() -> Unit)? = null
    internal var builderFinishLambda: (() -> Unit)? = null
    internal var builderSuccess: ((result: T) -> Unit) = {}
    internal var builderErrorLambda: (suspend CoroutineScope.(Throwable) -> Unit)? = null
}

fun <T> LoadExecutorBuilder<T>.execute(execution: suspend CoroutineScope.() -> T) {
    this.builderApiCall = execution
}

fun <T> LoadExecutorBuilder<T>.error(error: suspend CoroutineScope.(Throwable) -> Unit) {
    this.builderErrorLambda = error
}

fun <T> LoadExecutorBuilder<T>.onFinish(onFinish: () -> Unit) {
    this.builderFinishLambda = onFinish
}

fun <T> LoadExecutorBuilder<T>.onStart(onStart: () -> Unit) {
    this.builderStartLambda = onStart
}

fun <T> LoadExecutorBuilder<T>.success(success: (T) -> Unit) {
    this.builderSuccess = success
}
