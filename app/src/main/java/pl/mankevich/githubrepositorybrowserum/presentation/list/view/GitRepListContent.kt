package pl.mankevich.githubrepositorybrowserum.presentation.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import pl.mankevich.githubrepositorybrowserum.R
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.EmptyView
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.ErrorView
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.LoadingView
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.cast
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.rememberFlowWithLifecycle
import pl.mankevich.githubrepositorybrowserum.presentation.list.GitRepListViewState

@Composable
fun GitRepListContent(
    viewState: GitRepListViewState,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
) {
    val pagingGitRepItems = rememberFlowWithLifecycle(viewState.pagedData).collectAsLazyPagingItems()
    val stateContentModifier = modifier.padding(8.dp)

    if (pagingGitRepItems.loadState.refresh is LoadState.Loading) {
        LoadingView(stateContentModifier)
    }
    if (pagingGitRepItems.loadState.refresh is LoadState.Error) {
        ErrorView(
            error = pagingGitRepItems.loadState.refresh.cast<LoadState.Error>().error,
            modifier = stateContentModifier
        ) {
            pagingGitRepItems.retry()
        }
    }
    if (pagingGitRepItems.loadState.append.endOfPaginationReached) {
        if (pagingGitRepItems.itemCount < 1) {
            EmptyView(
                text = stringResource(id = R.string.git_rep_list_empty),
                modifier = stateContentModifier
            )
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        val cardModifier = Modifier.fillMaxWidth()

        items(pagingGitRepItems) { gitRep ->
            gitRep?.let {
                GitRepItemCard(
                    gitRepSimpleDto = gitRep,
                    modifier = cardModifier,
                ) {
                    onDetailClick.invoke(gitRep.name)
                }
            }
        }

        val stateItemModifier = cardModifier.padding(8.dp)

        if (pagingGitRepItems.loadState.append is LoadState.Loading) {
            item {
                LoadingView(
                    modifier = stateItemModifier
                )
            }
        }
        if (pagingGitRepItems.loadState.append is LoadState.Error) {
            item {
                ErrorView(
                    error = pagingGitRepItems.loadState.append.cast<LoadState.Error>().error,
                    modifier = stateItemModifier
                ) {
                    pagingGitRepItems.retry()
                }
            }
        }
    }
}