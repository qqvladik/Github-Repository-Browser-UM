package pl.mankevich.githubrepositorybrowserum.data.model.remote.dto

data class GitRepDetailDto(
    val ownerLogin: String,
    val name: String,
    val description: String?,
    val commitsNumber: Int,
    val issuesNumber: Int
)