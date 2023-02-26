package pl.mankevich.githubrepositorybrowserum.core.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
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
fun ErrorView(
    error: Throwable,
    modifier: Modifier = Modifier,
    action: () -> Unit,
) {
    //TODO add using interface for user's error handling in future
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            error.message ?: "Unknown error occurred", style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center, fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = action
        ) {
            Text("Retry")
        }
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
fun ErrorPageViewPreview() {
    GithubRepositoryBrowserUMTheme() {
        ErrorView(error = Exception("Error")) {}
    }
}