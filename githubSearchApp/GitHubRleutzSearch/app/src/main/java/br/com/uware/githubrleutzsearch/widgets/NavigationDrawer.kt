package br.com.uware.githubrleutzsearch.navigation

import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import br.com.uware.githubrleutzsearch.MainViewModel
import br.com.uware.githubrleutzsearch.widgets.DrawerMenu


/**
 * NavigationDrawer
 *
 *
 * @author Rodrigo Leutz(rodrigo@uware.com.br).
 */
@Composable
fun DrawerLayout(mainViewModel: MainViewModel, content: @Composable () -> Unit) {
    ModalDrawer(
        drawerState = mainViewModel.drawerState!!,
        drawerContent = {
            DrawerMenu(mainViewModel = mainViewModel)
        },
        content = {
            content()
        }
    )
}