package br.com.las.tvmazechallenge.presentation.scenes

import androidx.navigation.NavGraphBuilder

interface SceneContract<in T: SceneEvents> {
    /**
     * local values
     */
    val plainDestination: String
    val destination: String

    /**
     * Function that will be called during the creation of the ObjectNavGraph so that each scene can insert itself
     *
     * @param navGraphBuilder
     * @param featuredEvents
     */
    fun onCreateNavGraph(navGraphBuilder: NavGraphBuilder, featuredEvents: T)

}

interface SceneEvents