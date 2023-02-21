package pl.mankevich.githubrepositorybrowserum.presentation.navigation

interface NavigationProvider {

    fun openGitRepDetail(ownerLogin: String, name: String)

    fun openGitRepList(ownerLogin: String)

    fun navigateUp()
}