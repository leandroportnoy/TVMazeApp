package br.com.las.tvmazechallenge.presentation.scenes.main

import br.com.las.tvmazechallenge.presentation.scenes.SceneContract
import br.com.las.tvmazechallenge.presentation.scenes.SceneEvents

sealed interface MainScreen : SceneContract<MainScreenEvents>

interface MainScreenEvents : SceneEvents {

    fun onItemSelected(showId : Long)
}