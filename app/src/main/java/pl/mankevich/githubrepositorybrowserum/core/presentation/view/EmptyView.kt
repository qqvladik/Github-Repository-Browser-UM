package pl.mankevich.githubrepositorybrowserum.core.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import pl.mankevich.githubrepositorybrowserum.ui.theme.GithubRepositoryBrowserUMTheme

@Composable
fun EmptyView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
//            text = stringResource(id = R.string.text_no_data_found),//TODO add string resource
            text = "data not found",//TODO
            style = MaterialTheme
                .typography
                .h5,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode"
)
@Composable
fun EmptyPageViewPreview() {
    GithubRepositoryBrowserUMTheme() {
        EmptyView()
    }
}