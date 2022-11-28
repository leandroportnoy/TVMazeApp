package br.com.las.tvmazechallenge.presentation.scenes.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.las.data.repositories.TVMazeRepository
import br.com.las.data.repositories.models.TVShowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainScreenViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private  val tvMazeRepository: TVMazeRepository
): ViewModel() {

    /**
     * local vars
     */
    lateinit var screenEventsHandler : MainScreenEvents

    /**
     * local val
     */
    private val currentShowList : MutableList<TVShowModel> = mutableListOf()
    private val _screenState : MutableStateFlow<FetchState> = MutableStateFlow(FetchState.Idle)
    private var currentPageIndex = 0

    val screenState : StateFlow<FetchState> = _screenState

    init {
        viewModelScope.launch {
            currentShowList.addAll(tvMazeRepository.getShows(currentPageIndex))
            _screenState.value = FetchState.Success(currentShowList, currentPageIndex)
        }
    }

    fun onItemClicked(tvShow: TVShowModel) {
        screenEventsHandler.onItemSelected(tvShow.id)
    }

    fun onRequestMoreData() {
        viewModelScope.launch {
            currentShowList.addAll(tvMazeRepository.getShows(++currentPageIndex))
            _screenState.value = FetchState.Success(currentShowList, currentPageIndex)
        }
    }

    sealed class FetchState {
        object Idle : FetchState()
        data class Success(val tvShows : List<TVShowModel>, val page : Int) : FetchState()
    }

}