package br.com.las.tvmazechallenge.presentation.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import br.com.las.tvmazechallenge.presentation.NavGraph
import br.com.las.tvmazechallenge.presentation.scenes.main.MainScreen
import br.com.las.tvmazechallenge.presentation.scenes.showdetails.ShowDetailsScreen
import br.com.las.tvmazechallenge.presentation.scenes.splash.SplashScreen
import br.com.las.tvmazechallenge.ui.theme.TVMazeChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var splashScreen: SplashScreen

    @Inject
    lateinit var mainScreen: MainScreen

    @Inject
    lateinit var showDetailsScreen: ShowDetailsScreen

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
        val startingScreen = mainScreen
        NavGraph(
            startDestination = startingScreen,
            splashScreen = splashScreen,
            mainScreen = mainScreen,
            showDetailsScreen = showDetailsScreen
        )
    }
}
