package br.com.las.tvmazechallenge.presentation.scenes.showdetails

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


internal class ShowDetailsScreenImpl @Inject constructor() : ShowDetailsScreen {

    override val plainDestination = "show_details_screen"
    override val destination = plainDestination + "/{" + ShowDetailsScreen.SHOW_DETAIL_ID_ARGS + "}"

    override fun onCreateNavGraph(
        navGraphBuilder: NavGraphBuilder,
        featureEvents: ShowDetailsScreenEvents
    ) {
        navGraphBuilder.composable(
            destination,
            listOf(navArgument(ShowDetailsScreen.SHOW_DETAIL_ID_ARGS) { type = NavType.LongType })
        ) {
            val viewModel = hiltViewModel<ShowDetailsScreenViewModel>()
            viewModel.screenEventsHandler = featureEvents
            ShowDetailsScreenView(viewModel = viewModel)
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
internal abstract class ShowDetailsScreenProvider {

    @ActivityScoped
    @Binds
    abstract fun getShowDetailsScreenFeature(api: ShowDetailsScreenImpl): ShowDetailsScreen
}