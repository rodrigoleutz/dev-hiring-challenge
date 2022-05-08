package br.com.uware.githubrleutzsearch.screens.detailsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.uware.core.model.GitHubSearch
import br.com.uware.githubrleutzsearch.MainViewModel
import br.com.uware.githubrleutzsearch.R


/**
 * DetailsScreen
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 08/05/2022 - Initial release.
 */
@Composable
fun DetailsScreen(mainViewModel: MainViewModel, id: String?) {
    val fullList: GitHubSearch = mainViewModel.repositories.value!!
    val item = fullList.items?.find { it.id == id }
    if(item != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = stringResource(id = R.string.stars),
                        tint = Color.Yellow
                    )
                    item.stargazers_count?.let { Text(text = it.toString()) }
                }
                Row {
                    Text(text = "${stringResource(id = R.string.repository)}: ")
                    item.name?.let { Text(text = it) }
                }
                Row {
                    Text(text = "${stringResource(id = R.string.language)}: ")
                    item.language?.let { Text(text = it) }
                }
                Row {
                    Text(text = "${stringResource(id = R.string.owner)}: ")
                    item.owner?.login?.let { Text(text = it) }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    val mainViewModel = MainViewModel()
    DetailsScreen(mainViewModel, "")
}