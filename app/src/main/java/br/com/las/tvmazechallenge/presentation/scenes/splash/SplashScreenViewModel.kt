package br.com.las.tvmazechallenge.presentation.scenes.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
): ViewModel() {

    /**
     * local vars
     */
    lateinit var screenEventsHandler : SplashScreenEvents




}