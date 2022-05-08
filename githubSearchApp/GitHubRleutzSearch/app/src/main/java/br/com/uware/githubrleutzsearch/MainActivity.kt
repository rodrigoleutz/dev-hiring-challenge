package br.com.uware.githubrleutzsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.uware.githubrleutzsearch.navigation.GitHubNavigation
import br.com.uware.githubrleutzsearch.navigation.DrawerLayout
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()
        setContent {
            MaterialTheme {
                GitHubNavigation(mainViewModel = mainViewModel)
            }
        }
    }
}


@Composable
fun MyApp(
    mainViewModel: MainViewModel,
    drawerIcon: @Composable () -> Unit = {
        IconButton(onClick = {
            mainViewModel.coroutineScope?.launch {
                mainViewModel.drawerState?.open()
            }
        }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = stringResource(id = R.string.menu_description)
            )
        }
    },
    content: @Composable () -> Unit
) {
    DrawerLayout(mainViewModel = mainViewModel) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = colorResource(id = R.color.green),
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    contentColor = Color.White,
                    navigationIcon = {
                        drawerIcon()
                    }
                )
            },
            drawerElevation = 8.dp,
            drawerContentColor = Color.White,
            drawerGesturesEnabled = true,
            backgroundColor = colorResource(id = R.color.gray)
        ) {
            content()
        }
    }
}

