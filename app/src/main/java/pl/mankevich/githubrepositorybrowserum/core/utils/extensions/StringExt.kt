package pl.mankevich.githubrepositorybrowserum.core.utils.extensions

fun String.extractInt(): Int {
    return this.filter {
        it.isDigit()
    }.toInt()
}