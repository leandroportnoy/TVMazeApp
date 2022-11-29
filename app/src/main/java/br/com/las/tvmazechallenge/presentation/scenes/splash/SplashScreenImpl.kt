package br.com.las.tvmazechallenge.presentation.scenes.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

internal class SplashScreenImpl @Inject constructor() : SplashScreen {

    override val plainDestination: String = "splash_screen"
    override val destination = plainDestination

    override fun onCreateNavGraph(navGraphBuilder: NavGraphBuilder, featuredEvents: SplashScreenEvents) {
        navGraphBuilder.composable(destination) {
            splashScreenView()
        }
    }

}

@Module
@InstallIn(ActivityComponent::class)
internal abstract class SplashScreenProvider {
    @ActivityScoped
    @Binds
    abstract fun getSplashScreenScene(api: SplashScreenImpl): SplashScreen
}