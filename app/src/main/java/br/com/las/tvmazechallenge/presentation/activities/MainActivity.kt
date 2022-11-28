package br.com.las.tvmazechallenge.presentation.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import br.com.las.tvmazechallenge.TheMazeChallengeApplication
import br.com.las.tvmazechallenge.presentation.NavGraph
import br.com.las.tvmazechallenge.presentation.scenes.main.MainScreen
import br.com.las.tvmazechallenge.ui.theme.TVMazeChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var mainScreen: MainScreen

//    @Inject
//    lateinit var showDetailsScreen: ShowDetailsScreen
//
//    @Inject
//    lateinit var episodeDetailsScreen: EpisodeDetailsScreen
//
//    @Inject
//    lateinit var pinCodeScreen: PinCodeScreen
//
//    @Inject
//    lateinit var sharedPref: SettingsApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TVMazeChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavGraph()
                }
            }
        }
    }

    @Composable
    fun BuildNavGraph() {
//        val startingScreen = mainScreen
//        val startingScreen = if (sharedPref.isProvisioned() && !sharedPref.isProtected()) {
//            mainScreen
//        } else {
//            pinCodeScreen
//        }
        if (this::mainScreen.isInitialized)
            NavGraph(
                startDestination = mainScreen,
                mainScreen = mainScreen
    //            showDetailsScreen = showDetailsScreen,
    //            episodeDetailsScreen = episodeDetailsScreen,
    //            pinCodeScreen = pinCodeScreen
            )
    }
}
