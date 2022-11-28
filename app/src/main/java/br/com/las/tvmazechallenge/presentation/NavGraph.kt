package br.com.las.tvmazechallenge.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.las.tvmazechallenge.presentation.scenes.SceneContract
import br.com.las.tvmazechallenge.presentation.scenes.main.MainScreen
import br.com.las.tvmazechallenge.presentation.scenes.main.MainScreenEvents

@Composable
fun NavGraph(
    startDestination: SceneContract<*>,
    mainScreen: MainScreen
//    ,
//    showDetailsScreen: ShowDetailsScreen,
//    episodeDetailsScreen: EpisodeDetailsScreen,
//    pinCodeScreen: PinCodeScreen
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination.destination) {
        mainScreen.onCreateNavGraph(this, object : MainScreenEvents {
            override fun onItemSelected(showId: Long) {
                //navController.navigate(showDetailsScreen.plainDestination + "/" + showId)
            }
        })

//        showDetailsScreen.onCreateNavGraph(this, object : ShowDetailsScreenEvents {
//            override fun onEpisodeSelected(episodeId: Long) {
//                navController.navigate(episodeDetailsScreen.plainDestination + "/" + episodeId)
//            }
//        })

//        episodeDetailsScreen.onCreateNavGraph(this, object : EpisodeDetailsScreenEvents {})

//        pinCodeScreen.onCreateNavGraph(this, object : PinCodeScreenEvents {
//            override fun onPinCodeCompleted(authorized : Boolean) {
//                navController.popBackStack()
//                navController.navigate(mainScreen.plainDestination)
//            }
//        })
    }
}