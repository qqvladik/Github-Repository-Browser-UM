package pl.mankevich.githubrepositorybrowserum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import pl.mankevich.githubrepositorybrowserum.presentation.navigation.NavGraph
import pl.mankevich.githubrepositorybrowserum.ui.theme.GithubRepositoryBrowserUMTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubRepositoryBrowserUMTheme {
                val modifier = Modifier.fillMaxSize()
                Surface(modifier = modifier, color = MaterialTheme.colors.background) {
                    NavGraph(modifier = modifier)
                }
            }
        }
    }
}
