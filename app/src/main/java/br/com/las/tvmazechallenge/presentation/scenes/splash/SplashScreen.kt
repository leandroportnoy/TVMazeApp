package br.com.las.tvmazechallenge.presentation.scenes.splash

import br.com.las.tvmazechallenge.presentation.scenes.SceneContract
import br.com.las.tvmazechallenge.presentation.scenes.SceneEvents

sealed interface SplashScreen : SceneContract<SplashScreenEvents>

interface SplashScreenEvents: SceneEvents {
    fun navigate()
}