package pl.mankevich.githubrepositorybrowserum.presentation.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.navDeepLink
import pl.mankevich.githubrepositorybrowserum.core.utils.APP_BASE_DEEP_LINK
import pl.mankevich.githubrepositorybrowserum.core.utils.GIT_URI

sealed class Screen(val route: String) {

    object GitRepList : Screen(route = APP_BASE_DEEP_LINK.plus("?ownerLogin={ownerLogin}")) {

        fun destination(ownerLogin: String) = APP_BASE_DEEP_LINK.plus("?ownerLogin=$ownerLogin")

        fun getDeepLinks() = listOf(navDeepLink { uriPattern = "$GIT_URI/{ownerLogin}" })

        fun getOwnerLoginFromEntry(backStackEntry: NavBackStackEntry): String? {
            return backStackEntry.arguments?.getString("ownerLogin")
        }
    }

    object GitRepDetail : Screen(route = APP_BASE_DEEP_LINK.plus("?ownerLogin={ownerLogin}&name={name}")) {

        fun destination(ownerLogin: String, name: String) = APP_BASE_DEEP_LINK.plus("?ownerLogin=$ownerLogin&name=$name")

        fun getDeepLinks() = listOf(navDeepLink { uriPattern = "$GIT_URI/{ownerLogin}/{name}" })

        fun getOwnerLoginFromEntry(backStackEntry: NavBackStackEntry): String? {
            return backStackEntry.arguments?.getString("ownerLogin")
        }

        fun getNameFromEntry(backStackEntry: NavBackStackEntry): String? {
            return backStackEntry.arguments?.getString("name")
        }
    }
}