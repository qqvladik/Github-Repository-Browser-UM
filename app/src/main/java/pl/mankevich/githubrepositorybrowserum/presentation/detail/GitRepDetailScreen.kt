package pl.mankevich.githubrepositorybrowserum.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
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
    navigator: NavigationProvider,
    name: String,
    ownerLogin: String,
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
        topBar = {
            NavBackAppBar(
                title = (name),
                pressOnBack = {
                    navigator.navigateUp()
                }
            )
        },
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
                uiState.cast<BaseViewState.Error>().error,
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

@Composable
fun NavBackAppBar(
    title: String,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { pressOnBack.invoke() }
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}