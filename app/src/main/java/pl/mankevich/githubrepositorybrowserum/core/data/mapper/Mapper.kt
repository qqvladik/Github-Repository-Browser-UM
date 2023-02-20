package pl.mankevich.githubrepositorybrowserum.core.data.mapper

interface Mapper<Input, Output> {
    fun map(input: Input): Output?
}