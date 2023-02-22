package pl.mankevich.githubrepositorybrowserum.presentation.list.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.ErrorView
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.LoadingView
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.cast
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.rememberFlowWithLifecycle
import pl.mankevich.githubrepositorybrowserum.presentation.list.GitRepListViewState

@Composable
fun GitRepListContent(
    paddingValues: PaddingValues,
    viewState: GitRepListViewState,
    onDetailClick: (String) -> Unit = {}
) {

    val pagingGitRepItems = rememberFlowWithLifecycle(viewState.pagedData).collectAsLazyPagingItems() //TODO Попробовать сделать remember savable

    if (pagingGitRepItems.loadState.refresh is LoadState.Loading) {
        LoadingView()
    }
    if (pagingGitRepItems.loadState.refresh is LoadState.Error) {
        ErrorView(pagingGitRepItems.loadState.refresh.cast<LoadState.Error>().error) {
            pagingGitRepItems.retry()
        }
    }

    LazyColumn(contentPadding = paddingValues) {
        items(pagingGitRepItems) { gitRep ->
            gitRep?.let {
                GitRepItemCard(
                    gitRepSimpleDto = gitRep,
                    onDetailClick = {
                        onDetailClick.invoke(gitRep.name)
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
            item {
                ErrorView(pagingGitRepItems.loadState.append.cast<LoadState.Error>().error) {
                    pagingGitRepItems.retry()
                }
            }
        }
    }
}