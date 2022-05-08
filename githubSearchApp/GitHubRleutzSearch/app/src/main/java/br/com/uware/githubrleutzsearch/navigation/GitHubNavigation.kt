package br.com.uware.githubrleutzsearch.navigation

import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.uware.githubrleutzsearch.MainViewModel
import br.com.uware.githubrleutzsearch.MyApp
import br.com.uware.githubrleutzsearch.screens.detailsScreen.DetailsScreen
import br.com.uware.githubrleutzsearch.screens.gitHubSearch.GitHubSearch
import br.com.uware.githubrleutzsearch.screens.home.HomeScreen
import br.com.uware.githubrleutzsearch.screens.splash.SplashScreen


/**
 * GitHubNavigation
 *
 *
 * @author Rodrigo Leutz(rodrigo@uware.com.br).
 */
@Composable
fun GitHubNavigation(mainViewModel: MainViewModel) {

    // Set variables of mainViewModel
    mainViewModel.navHostController = rememberNavController()
    mainViewModel.drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    mainViewModel.coroutineScope = rememberCoroutineScope()

    // Routes
    NavHost(
        navController = mainViewModel.navHostController!!,
        startDestination = GitHubScreens.SplashScreen.name
    ) {
        composable(GitHubScreens.SplashScreen.name) {
            SplashScreen(mainViewModel = mainViewModel)
        }

        composable(GitHubScreens.HomeScreen.name) {
            MyApp(mainViewModel = mainViewModel) {
                HomeScreen(mainViewModel = mainViewModel)
            }
        }

        composable(GitHubScreens.GitHubSearch.name) {
            MyApp(mainViewModel = mainViewModel) {
                GitHubSearch(mainViewModel = mainViewModel)
            }
        }

        composable(GitHubScreens.DetailsScreen.name + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            })
        ) {
            MyApp(mainViewModel = mainViewModel) {
                DetailsScreen(mainViewModel = mainViewModel, id = it.arguments?.getString("id"))
            }
        }
    }
}