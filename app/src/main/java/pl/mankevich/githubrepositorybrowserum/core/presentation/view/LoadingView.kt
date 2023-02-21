package pl.mankevich.githubrepositorybrowserum.core.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mankevich.githubrepositorybrowserum.ui.theme.GithubRepositoryBrowserUMTheme

@Composable
fun LoadingView() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .size(35.dp)
            .wrapContentSize(Alignment.Center),
        strokeWidth = 5.dp
    )
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun LoadingViewPreview() {
    GithubRepositoryBrowserUMTheme() {
        LoadingView()
    }
}