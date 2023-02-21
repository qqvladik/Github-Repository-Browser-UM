package pl.mankevich.githubrepositorybrowserum.presentation.navigation

import androidx.navigation.NavController

class NavigationProviderImpl constructor(
    private val navController: NavController
) : NavigationProvider {

    override fun openGitRepDetail(ownerLogin: String, name: String) {
        navController.navigate(
            Screen.GitRepDetail.destination(
                ownerLogin = ownerLogin,
                name = name
            )
        )
    }

    override fun openGitRepList(ownerLogin: String) {
        navController.navigate(
            Screen.GitRepList.destination(
                ownerLogin = ownerLogin
            )
        )
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}