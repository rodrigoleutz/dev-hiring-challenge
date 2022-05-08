package br.com.uware.githubrleutzsearch.navigation


/**
 * GitHubScreens
 *
 *
 * @author Rodrigo Leutz(rodrigo@uware.com.br).
 */
enum class GitHubScreens {
    SplashScreen,
    GitHubSearch,
    HomeScreen,
    DetailsScreen;
    companion object {
        fun fromRoute(route: String?): GitHubScreens =
            when(route?.substringBefore("/")){
                SplashScreen.name -> SplashScreen
                HomeScreen.name -> HomeScreen
                GitHubSearch.name -> GitHubSearch
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}