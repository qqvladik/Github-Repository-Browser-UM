package pl.mankevich.githubrepositorybrowserum.core.utils.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.mankevich.githubrepositorybrowserum.data.remote.error.ErrorEntity

fun <T> ViewModel.networkExecutor(networkBuilder: NetworkBuilder<T>.() -> Unit) {
    viewModelScope.networkOperation(networkBuilder)
}

fun <T> CoroutineScope.networkOperation(networkBuilder: NetworkBuilder<T>.() -> Unit) {
    this.launch {
        val builder = NetworkBuilder<T>().apply { networkBuilder() }
        try {
            builder.builderStartLambda?.invoke()

            builder.builderLoadingLiveData?.value = true

            val result = builder.builderApiCall?.invoke(this)!!

            builder.builderSuccess.invoke(result)


        } catch (e: ErrorEntity) { //TODO подумать, может создать интерфейс ErrorEntity
            builder.builderErrorLambda?.invoke(this, e)
        } finally {
            builder.builderFinishLambda?.invoke()
            builder.builderLoadingLiveData?.value = false
        }
    }
}