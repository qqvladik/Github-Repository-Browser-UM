package pl.mankevich.githubrepositorybrowserum.data.model.remote.request

import pl.mankevich.githubrepositorybrowserum.core.utils.PAGE_SIZE

data class GitRepListRequest(
    val ownerLogin: String,
    val pageSize: Int = PAGE_SIZE,
    val cursor: String?
)
