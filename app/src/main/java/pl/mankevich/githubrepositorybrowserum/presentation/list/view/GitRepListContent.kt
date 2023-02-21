package pl.mankevich.githubrepositorybrowserum.presentation.list.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.ErrorView
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.LoadingView
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.rememberFlowWithLifecycle
import pl.mankevich.githubrepositorybrowserum.presentation.list.GitRepListViewState

@Composable
fun GitRepListContent(
    paddingValues: PaddingValues,
    viewState: GitRepListViewState,
    onDetailClick: (String) -> Unit = {}
) {

    val pagingGitRepItems = rememberFlowWithLifecycle(viewState.pagedData).collectAsLazyPagingItems()

    if (pagingGitRepItems.loadState.refresh is LoadState.Loading) {//TODO работает только эта загрузка, та что в скрине - нет. Возможно изза отсутствия paddingValues
        LoadingView()
    }
    LazyColumn(contentPadding = paddingValues) {
        items(pagingGitRepItems) { gitRep ->
            gitRep?.let {
                GitRepItemCard(
                    gitRepSimpleDto = gitRep,
                    onDetailClick = {
                        onDetailClick.invoke(gitRep.name!!) //TODO сделать чтобы имя было не нулл
                    }
                )
            }
        }
        if (pagingGitRepItems.loadState.append is LoadState.Loading) {
            item {
                LoadingView()
            }
        }
        if (pagingGitRepItems.loadState.append is LoadState.Error) {
            val state = pagingGitRepItems.loadState.append as LoadState.Error
            val errorMessage = state.error.localizedMessage ?: "unknown error occurred"
            item {
                ErrorView(errorMessage) {
                    pagingGitRepItems.retry()
                }
            }
        }
    }
}