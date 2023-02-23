package pl.mankevich.githubrepositorybrowserum.core.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mankevich.githubrepositorybrowserum.core.presentation.theme.GithubRepositoryBrowserUMTheme

@Suppress("ForbiddenComment")
@Composable
fun EmptyView(
    text: String = "Empty"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text,
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center, fontSize = 16.sp
        )
    }
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
fun EmptyPageViewPreview() {
    GithubRepositoryBrowserUMTheme() {
        EmptyView()
    }
}