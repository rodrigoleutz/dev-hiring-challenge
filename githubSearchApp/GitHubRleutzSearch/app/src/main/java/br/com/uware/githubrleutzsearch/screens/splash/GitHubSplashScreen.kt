package br.com.uware.githubrleutzsearch.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.uware.githubrleutzsearch.MainViewModel
import br.com.uware.githubrleutzsearch.R
import br.com.uware.githubrleutzsearch.navigation.GitHubScreens
import kotlinx.coroutines.delay


/**
 * GitHubSplashScreen
 *
 *
 * @author Rodrigo Leutz
 */
@Composable
fun SplashScreen(mainViewModel: MainViewModel){
    val scale = remember {
        Animatable(0f)
    }
    val rotate = remember {
        Animatable(0f)
    }
    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 1800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(2000L)
        mainViewModel.navHostController?.navigate(GitHubScreens.HomeScreen.name)
    }
    // Center animated content
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.green))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .scale(scale.value)
                .rotate(rotate.value),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(colorResource(id = R.color.green)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "IMC Icon",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp),
                        tint = Color.White
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}