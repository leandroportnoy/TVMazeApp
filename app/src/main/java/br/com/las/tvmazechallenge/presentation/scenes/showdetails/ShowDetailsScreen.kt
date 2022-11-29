package br.com.las.tvmazechallenge.presentation.scenes.showdetails


import br.com.las.tvmazechallenge.presentation.scenes.SceneContract
import br.com.las.tvmazechallenge.presentation.scenes.SceneEvents

sealed interface ShowDetailsScreen : SceneContract<ShowDetailsScreenEvents> {

    companion object {
        internal const val SHOW_DETAIL_ID_ARGS: String = "show_id"
    }
}

interface ShowDetailsScreenEvents : SceneEvents {
    fun onEpisodeSelected(episodeId: Long)
}