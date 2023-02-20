package pl.mankevich.githubrepositorybrowserum.core.utils.extensions

inline fun <reified T : Any> Any.cast(): T {
    return this as T
}