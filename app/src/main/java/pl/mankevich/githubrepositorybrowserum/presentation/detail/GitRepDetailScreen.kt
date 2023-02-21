package pl.mankevich.githubrepositorybrowserum.presentation.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.EmptyView
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.ErrorView
import pl.mankevich.githubrepositorybrowserum.core.presentation.view.LoadingView
import pl.mankevich.githubrepositorybrowserum.core.presentation.viewModel.mvi.BaseViewState
import pl.mankevich.githubrepositorybrowserum.core.utils.extensions.cast
import pl.mankevich.githubrepositorybrowserum.presentation.detail.view.GitRepDetailContent
import pl.mankevich.githubrepositorybrowserum.presentation.navigation.NavigationProvider

@Composable
fun GitRepDetailScreen(
    navigator: NavigationProvider,//TODO использовать в кнопке назад
    name: String = "CurrencyExchanger",
    ownerLogin: String = "qqvladik",
    modifier: Modifier
) {
    val viewModel: GitRepDetailViewModel = hiltViewModel()
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(
            GitRepDetailEvent.LoadDetail(
                name = name,
                ownerLogin = ownerLogin
            )
        )
    })

    Scaffold(
        topBar = { TopAppBar(title = { Text(name) }) },//TODO добавить стрелку назад
        modifier = Modifier.fillMaxSize()
    ) { paddings ->

        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is BaseViewState.Data -> GitRepDetailContent(
                paddingValues = paddings,
                viewState = uiState.cast<BaseViewState.Data<GitRepDetailViewState>>().value
            )
            is BaseViewState.Empty -> EmptyView(modifier = modifier)
            is BaseViewState.Error -> ErrorView(
                errorMessage = uiState.cast<BaseViewState.Error>().error.message!!,//TODO в error message нуллабл, что-то с этим сделать,
                // а лучше туда прям error кидать, сделать интерфейс с методом получения текста из ошибки
                action = {
                    viewModel.onTriggerEvent(
                        GitRepDetailEvent.LoadDetail(
                            name = name,
                            ownerLogin = ownerLogin
                        )
                    )
                }
            )
            is BaseViewState.Loading -> LoadingView()

        }
    }
}