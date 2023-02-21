package pl.mankevich.githubrepositorybrowserum.presentation.list

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto

data class GitRepListViewState(
    val pagedData: Flow<PagingData<GitRepSimpleDto>> = emptyFlow()
)

sealed class GitRepListEvent {

    data class LoadList(
        val ownerLogin: String
    ) : GitRepListEvent()
}