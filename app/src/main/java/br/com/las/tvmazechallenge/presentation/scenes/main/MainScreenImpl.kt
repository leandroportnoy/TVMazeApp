package br.com.las.tvmazechallenge.presentation.scenes.main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

internal class MainScreenImpl @Inject constructor() : MainScreen {

    override val plainDestination = "main_screen"

    override val destination = plainDestination

    override fun onCreateNavGraph(navGraphBuilder: NavGraphBuilder, mainEvents: MainScreenEvents) {
        navGraphBuilder.composable(destination) {
            val mainViewModel = hiltViewModel<MainScreenViewModel>()
            mainViewModel.screenEventsHandler = mainEvents
            mainScreenView(viewModel = mainViewModel)
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
internal abstract class MainScreenProvider {
    @ActivityScoped
    @Binds
    abstract fun getMainScreenScene(api: MainScreenImpl): MainScreen
}
