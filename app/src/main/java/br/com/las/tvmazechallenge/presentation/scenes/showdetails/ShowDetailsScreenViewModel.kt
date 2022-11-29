package br.com.las.tvmazechallenge.presentation.scenes.showdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.las.data.repositories.TVMazeRepository
import br.com.las.data.repositories.models.EpisodeModel
import br.com.las.data.repositories.models.TVShowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ShowDetailsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val tvShowRepository: TVMazeRepository
) : ViewModel() {

    lateinit var screenEventsHandler: ShowDetailsScreenEvents

    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screenState

    init {
        val showId = savedStateHandle.get<Long>(ShowDetailsScreen.SHOW_DETAIL_ID_ARGS)
        if(showId != null) {
            viewModelScope.launch {
                val tvShowDetail = tvShowRepository.getShowById(showId)
//                _screenState.value = ScreenState.Fetched(tvShowDetail)
                val episodes = tvShowRepository.getEpisodes(showId)
                _screenState.value = ScreenState.Fetched(tvShowDetail, episodes)
            }
        }
    }

    fun onEpisodeClicked(episodeId : Long) {
        screenEventsHandler.onEpisodeSelected(episodeId = episodeId)
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        data class Fetched(val tvShowDetail: TVShowModel, val episodes : List<EpisodeModel>? = null) : ScreenState()
//        data class Fetched(val tvShowDetail: TVShowModel, val episodes : List<List<EpisodeModel>>? = null) : ScreenState()
    }
}