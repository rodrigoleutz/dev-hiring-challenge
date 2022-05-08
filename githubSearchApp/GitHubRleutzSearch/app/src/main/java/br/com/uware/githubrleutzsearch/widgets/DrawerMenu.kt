package br.com.uware.githubrleutzsearch.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ScreenShare
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.uware.githubrleutzsearch.MainViewModel
import br.com.uware.githubrleutzsearch.R
import br.com.uware.githubrleutzsearch.navigation.GitHubScreens
import kotlinx.coroutines.launch


/**
 * DrawerMenu
 *
 * @author Rodrigo Leutz(rodrigo@uware.com.br).
 */
@Composable
fun DrawerMenu(mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(colorResource(id = R.color.green_dark)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.ScreenShare,
                    contentDescription = "GitHubRleutzSearch Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
        DrawerButton(
            mainViewModel = mainViewModel,
            text = R.string.home,
            icon = Icons.Rounded.Home,
            navigate = GitHubScreens.HomeScreen.name
        )
        DrawerButton(
            mainViewModel = mainViewModel,
            text = R.string.github_search,
            icon = Icons.Rounded.ScreenShare,
            navigate = GitHubScreens.GitHubSearch.name
        )
    }
}

@Composable
fun DrawerButton(
    mainViewModel: MainViewModel,
    text: Int,
    icon: ImageVector,
    navigate: String
) {
    var currentRoute = mainViewModel.navHostController?.currentDestination?.route
    TextButton(
        onClick = {
            mainViewModel.coroutineScope?.launch {
                mainViewModel.drawerState?.close()
                mainViewModel.navHostController?.navigate(navigate)
            }
            currentRoute = mainViewModel.navHostController?.currentDestination?.route
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (currentRoute == navigate) colorResource(id = R.color.green)
            else Color.White
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.fillMaxWidth().height(40.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = text),
                tint = if (currentRoute == navigate) {
                    Color.White
                } else {
                    Color.DarkGray
                },
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = stringResource(id = text),
                textAlign = TextAlign.Center,
                color = if (currentRoute == navigate) {
                    Color.White
                } else {
                    Color.DarkGray
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerMenuPreview() {
    val mainViewModel = MainViewModel()
    DrawerMenu(mainViewModel = mainViewModel)
}