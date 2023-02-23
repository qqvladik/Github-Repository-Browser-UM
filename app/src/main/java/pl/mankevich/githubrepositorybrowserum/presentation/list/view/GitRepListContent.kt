package pl.mankevich.githubrepositorybrowserum.presentation.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.EmptyView
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

    val pagingGitRepItems = rememberFlowWithLifecycle(viewState.pagedData).collectAsLazyPagingItems()

    if (pagingGitRepItems.loadState.refresh is LoadState.Loading) {
        LoadingView()
    }
    if (pagingGitRepItems.loadState.refresh is LoadState.Error) {
        ErrorView(pagingGitRepItems.loadState.refresh.cast<LoadState.Error>().error) {
            pagingGitRepItems.retry()
        }
    }
    if ( pagingGitRepItems.loadState.append.endOfPaginationReached )
    {
        if ( pagingGitRepItems.itemCount < 1){
            EmptyView("Empty list")
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
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