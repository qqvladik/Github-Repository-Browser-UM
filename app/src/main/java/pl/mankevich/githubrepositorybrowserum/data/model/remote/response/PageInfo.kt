package pl.mankevich.githubrepositorybrowserum.data.model.remote.response

data class PageInfo(
    val startCursor: String?,
    val hasPreviousPage: Boolean,
    val endCursor: String?,
    val hasNextPage: Boolean
)
