package pl.mankevich.githubrepositorybrowserum.core.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
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
import pl.mankevich.githubrepositorybrowserum.ui.theme.GithubRepositoryBrowserUMTheme

@Suppress("ForbiddenComment")
@Composable
fun ErrorView(errorMessage: String, action: () -> Unit) {//TODO здесь обрабатывать ошибки можно
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .requiredHeight(80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            errorMessage, style = MaterialTheme.typography.button,
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
        ErrorView(errorMessage = "Error") {}
    }
}