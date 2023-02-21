package pl.mankevich.githubrepositorybrowserum.data.model.remote.response

import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto

data class GitRepsResponse(
    val totalCount: Int,
    val gitRepSimpleList: List<GitRepSimpleDto>,
    val pageInfo: PageInfo
)