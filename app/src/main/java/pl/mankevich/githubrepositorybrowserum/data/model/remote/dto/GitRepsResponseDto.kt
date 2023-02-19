package pl.mankevich.githubrepositorybrowserum.data.model.remote.dto

data class GitRepsResponseDto(
    val gitRepSimpleList: List<GitRepSimpleDto>,
    val startCursor: String?,
    val hasPreviousPage: Boolean,
    val endCursor: String?,
    val hasNextPage: Boolean
)