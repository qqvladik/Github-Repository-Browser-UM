package pl.mankevich.githubrepositorybrowserum.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.mankevich.githubrepositorybrowserum.R
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
    var ownerLoginNotNull by rememberSaveable { mutableStateOf(ownerLogin ?: "JakeWharton") }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(GitRepListEvent.LoadList(ownerLogin = ownerLoginNotNull))
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                elevation = 0.dp
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddings ->

        Column(Modifier.fillMaxSize()) {

            InputOwner(ownerLoginNotNull) { owner ->
                ownerLoginNotNull = owner
                viewModel.onTriggerEvent(GitRepListEvent.LoadList(ownerLogin = ownerLoginNotNull))
            }

            Divider(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .height(6.dp)
                    .fillMaxWidth()
            )

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
                is BaseViewState.Empty -> {}
                is BaseViewState.Error -> ErrorView(
                    error = uiState.cast<BaseViewState.Error>().error,
                    action = {
                        viewModel.onTriggerEvent(GitRepListEvent.LoadList(ownerLoginNotNull))
                    }
                )
                is BaseViewState.Loading -> LoadingView()
            }
        }

    }
}

@Composable
fun InputOwner(
    ownerLogin: String,
    action: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth()
    ) {
        var editText by rememberSaveable { mutableStateOf(ownerLogin) }
        TextField(
            value = editText,
            onValueChange = { editText = it.trim() },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onBackground,
                backgroundColor = MaterialTheme.colors.background,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(60.dp)
                .clickable {
                    action.invoke(editText)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowForward),
                contentDescription = null,
                modifier = Modifier
                    .background(Color.Transparent)
                    .size(30.dp)
            )
        }

    }
}