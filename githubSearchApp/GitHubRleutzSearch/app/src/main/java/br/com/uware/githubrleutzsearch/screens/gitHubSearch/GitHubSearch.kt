package br.com.uware.githubrleutzsearch.screens.gitHubSearch

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.uware.core.Constants
import br.com.uware.core.model.GitHubRepository
import br.com.uware.core.model.GitHubSearch
import br.com.uware.githubrleutzsearch.MainViewModel
import br.com.uware.githubrleutzsearch.R
import br.com.uware.githubrleutzsearch.components.InputTextField
import br.com.uware.githubrleutzsearch.navigation.GitHubScreens
import java.util.*


/**
 * GitHubSearch
 * Main content for screen.
 * @param mainViewModel MainViewModel
 * @author Rodrigo Leutz
 * @version 1.0.0 - 02/05/2022 - Initial release.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GitHubSearch(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchString = remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var expandedExtra by remember {
        mutableStateOf(false)
    }
    val repositories: GitHubSearch? by mainViewModel.repositories.observeAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column() {
                InputTextField(
                    valueState = searchString,
                    text = stringResource(id = R.string.search),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    imeAction = ImeAction.Done,
                    onAction = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
                Button(
                    onClick = {
                        if (searchString.value.isNotBlank()) {
                            mainViewModel.getGitHubRepository(
                                searchString.value
                            )
                            expanded = true
                            keyboardController?.hide()
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.search_empty),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.green),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.search))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp, end = 8.dp)
                        .clickable {
                            expandedExtra = !expandedExtra
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(text = stringResource(id = R.string.extra_options))
                    Icon(
                        imageVector = if (!expandedExtra) Icons.Rounded.ArrowDropDown
                        else Icons.Rounded.ArrowDropUp,
                        contentDescription = stringResource(
                            id = R.string.arrow_down
                        )
                    )
                }
                AnimatedVisibility(visible = expandedExtra) {
                    ExtraOptions(mainViewModel = mainViewModel)
                }
            }
        }
        if (repositories != null) {
            AnimatedVisibility(
                visible = expanded,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemList(mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun ExtraOptions(mainViewModel: MainViewModel) {
    val languages by mainViewModel.checkBoxLanguages.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        for (language in languages!!) {
            CheckBoxItem(
                mainViewModel = mainViewModel,
                checkItem = language.language
            )
        }
    }
}

@Composable
fun CheckBoxItem(
    mainViewModel: MainViewModel,
    checkItem: String
) {
    val checkBoxState = remember {
        mutableStateOf(true)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkBoxState.value,
            onCheckedChange = {
                checkBoxState.value = it
                mainViewModel.checkLanguage(checkItem)
            },
            colors = CheckboxDefaults.colors(colorResource(id = R.color.green))
        )
        Text(
            text = checkItem.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        )
    }
}

@Composable
fun ItemList(mainViewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        LazyColumn {
            items(mainViewModel.repositories.value?.items!!) { gitHubRepo ->
                ItemInList(
                    gitHubRepository = gitHubRepo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            mainViewModel.navHostController?.navigate(
                                GitHubScreens.DetailsScreen.name + "/${gitHubRepo.id}"
                            )
                        }
                )
                Divider(color = Color.Black, modifier = Modifier.height(1.dp))
            }
        }
    }
}

@Composable
fun ItemInList(
    gitHubRepository: GitHubRepository,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = stringResource(id = R.string.stars),
                tint = Color.Yellow
            )
            gitHubRepository.stargazers_count?.let { Text(text = it.toString()) }
        }
        Row {
            Text(text = "${stringResource(id = R.string.repository)}: ")
            gitHubRepository.name?.let { Text(text = it) }
        }
        Row {
            Text(text = "${stringResource(id = R.string.language)}: ")
            gitHubRepository.language?.let { Text(text = it) }
        }
        Row {
            Text(text = "${stringResource(id = R.string.owner)}: ")
            gitHubRepository.owner?.login?.let { Text(text = it) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GitHubSearchPreview() {
    val mainViewModel = MainViewModel()
    GitHubSearch(mainViewModel = mainViewModel)
}