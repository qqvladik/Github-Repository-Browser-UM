package pl.mankevich.githubrepositorybrowserum.core.mapper

interface Mapper<Input, Output> {
    fun map(input: Input): Output?
}