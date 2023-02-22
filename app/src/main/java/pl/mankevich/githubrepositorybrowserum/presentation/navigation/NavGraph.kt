package pl.mankevich.githubrepositorybrowserum.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.mankevich.githubrepositorybrowserum.presentation.detail.GitRepDetailScreen
import pl.mankevich.githubrepositorybrowserum.presentation.list.GitRepListScreen

@Composable
fun NavGraph(
    modifier: Modifier
) {
    val navController = rememberNavController()
    val navigator: NavigationProvider = NavigationProviderImpl(navController)
    NavHost(
        navController = navController,
        startDestination = Screen.GitRepList.route
    ) {
        composable(
            route = Screen.GitRepList.route,
            deepLinks = Screen.GitRepList.getDeepLinks()
        ) { backStackEntry ->
            GitRepListScreen(
                modifier = modifier,
                navigator = navigator,
                ownerLogin = Screen.GitRepList.getOwnerLoginFromEntry(backStackEntry)
            )
        }
        composable(
            route = Screen.GitRepDetail.route,
            deepLinks = Screen.GitRepDetail.getDeepLinks()
        ) { backStackEntry ->
            GitRepDetailScreen(
                modifier = modifier,
                navigator = navigator,
                name = Screen.GitRepDetail.getNameFromEntry(backStackEntry)!!,
                ownerLogin = Screen.GitRepDetail.getOwnerLoginFromEntry(backStackEntry)!!

            )
        }
    }
}