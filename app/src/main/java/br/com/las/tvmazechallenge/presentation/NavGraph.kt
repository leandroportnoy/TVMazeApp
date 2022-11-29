package br.com.las.tvmazechallenge.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.las.tvmazechallenge.presentation.scenes.SceneContract
import br.com.las.tvmazechallenge.presentation.scenes.main.MainScreen
import br.com.las.tvmazechallenge.presentation.scenes.main.MainScreenEvents
import br.com.las.tvmazechallenge.presentation.scenes.showdetails.ShowDetailsScreen
import br.com.las.tvmazechallenge.presentation.scenes.showdetails.ShowDetailsScreenEvents
import br.com.las.tvmazechallenge.presentation.scenes.splash.SplashScreen
import br.com.las.tvmazechallenge.presentation.scenes.splash.SplashScreenEvents
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavGraph(
    startDestination: SceneContract<*>,
    splashScreen: SplashScreen,
    mainScreen: MainScreen,
    showDetailsScreen: ShowDetailsScreen
) {

    val navController = rememberNavController()

    NavHost(navController, startDestination.destination) {

        //main screen
        mainScreen.onCreateNavGraph(this, object : MainScreenEvents {
            override fun onItemSelected(showId: Long) {
                navController.navigate(showDetailsScreen.plainDestination + "/" + showId)
            }
        })
        //show details
        showDetailsScreen.onCreateNavGraph(this, object : ShowDetailsScreenEvents {
            override fun onEpisodeSelected(episodeId: Long) {

            }
        })

    }
}