package pl.mankevich.githubrepositorybrowserum.data.model.remote.request

private const val DEFAULT_PAGE_SIZE = 20

data class GitRepListRequest(
    val ownerLogin: String,
    val pageSize: Int = DEFAULT_PAGE_SIZE
)
