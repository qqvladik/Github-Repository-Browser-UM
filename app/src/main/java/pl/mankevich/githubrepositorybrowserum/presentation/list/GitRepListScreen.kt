package pl.mankevich.githubrepositorybrowserum.presentation.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import pl.mankevich.githubrepositorybrowserum.R
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.EmptyView
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.ErrorView
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.LoadingView
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.mvi.BaseViewState
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.cast
import pl.mankevich.githubrepositorybrowserum.presentation.list.view.GitRepListContent
import pl.mankevich.githubrepositorybrowserum.presentation.navigation.NavigationProvider

@Composable
fun GitRepListScreen(
    navigator: NavigationProvider,
    modifier: Modifier,
    ownerLogin: String? = null
) {
    val viewModel: GitRepListViewModel = hiltViewModel()
    val ownerLoginNotNull = ownerLogin ?: "JakeWharton"

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(
            GitRepListEvent.LoadList(
                ownerLogin = ownerLoginNotNull
            )
        ) //TODO сделать задавание через editText, как уже описано выше
    })

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }) },
        modifier = Modifier.fillMaxSize()
    ) { paddings ->

        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is BaseViewState.Data -> GitRepListContent(
                paddingValues = paddings,
                viewState = uiState.cast<BaseViewState.Data<GitRepListViewState>>().value,
                onDetailClick = { name ->
                    navigator.openGitRepDetail(
                        ownerLogin = ownerLoginNotNull,
                        name = name
                    )
                }
            )
            is BaseViewState.Empty -> EmptyView(modifier = modifier)
            is BaseViewState.Error -> ErrorView(
                error = uiState.cast<BaseViewState.Error>().error,
                action = {
                    viewModel.onTriggerEvent(GitRepListEvent.LoadList(ownerLoginNotNull)) //TODO сделать задавание через editText, как уже описано выше
                }
            )
            is BaseViewState.Loading -> LoadingView()
        }
    }
}