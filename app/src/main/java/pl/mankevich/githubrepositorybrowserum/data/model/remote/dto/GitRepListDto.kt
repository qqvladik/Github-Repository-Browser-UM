package pl.mankevich.githubrepositorybrowserum.data.model.remote.dto

data class GitRepListDto(
    val gitRepSimpleList: List<GitRepSimpleDto>,
    val startCursor: String?,
    val hasPreviousPage: Boolean,
    val endCursor: String?,
    val hasNextPage: Boolean
)