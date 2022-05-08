package br.com.uware.githubrleutzsearch.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ScreenShare
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.uware.githubrleutzsearch.MainViewModel
import br.com.uware.githubrleutzsearch.R
import br.com.uware.githubrleutzsearch.navigation.GitHubScreens


/**
 * HomeScreen
 * HomeScreen for app.
 * @param mainViewModel MainViewModel
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 28 - Initial release.
 */
@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    Column {
        HomeButton(
            mainViewModel = mainViewModel,
            text = R.string.github_search,
            image = Icons.Rounded.ScreenShare,
            navigate = GitHubScreens.GitHubSearch.name
        )
    }
}

@Composable
fun HomeButton(
    mainViewModel: MainViewModel,
    text: Int,
    image: ImageVector,
    navigate: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                mainViewModel.navHostController?.navigate(navigate)
            },
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = image, contentDescription = stringResource(id = text))
            Text(
                text = stringResource(id = text),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val mainViewModel = MainViewModel()
    HomeScreen(mainViewModel = mainViewModel)
}