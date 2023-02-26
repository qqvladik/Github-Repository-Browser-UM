package pl.mankevich.githubrepositorybrowserum.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.AndroidEntryPoint
import pl.mankevich.githubrepositorybrowserum.R
import pl.mankevich.githubrepositorybrowserum.core.presentation.theme.GithubRepositoryBrowserUMTheme
import pl.mankevich.githubrepositorybrowserum.presentation.navigation.NavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubRepositoryBrowserUMTheme {
                val modifier = Modifier.fillMaxSize()
                Surface(modifier = modifier, color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }) },
                        modifier = modifier
                    ) {}
                    NavGraph(modifier = modifier)
                }
            }
        }
    }
}
