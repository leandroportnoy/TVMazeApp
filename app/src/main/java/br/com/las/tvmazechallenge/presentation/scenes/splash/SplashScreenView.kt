package br.com.las.tvmazechallenge.presentation.scenes.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.navigation.NavHost
import br.com.las.tvmazechallenge.R
import kotlinx.coroutines.delay

@Composable
internal fun splashScreenView() {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(3f).getInterpolation(it)
                }
            )
        )
        delay(3000L)

    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.tv_maze_image),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}