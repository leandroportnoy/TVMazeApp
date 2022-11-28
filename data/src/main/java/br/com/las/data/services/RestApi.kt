package br.com.las.data.services

import br.com.las.data.data.*

sealed interface RestApi {

    /**
     * Return a list of TV shows
     * @param page Page to be fetched
     */
    suspend fun getShows(page: Int): List<TVShowDetailsResponse>

    /**
     * Return detailed info about a show by showId
     * @param showId Show ID on TVMaze
     * @return [TVShowDetail] object containing all details about a show
     */
    suspend fun getShowById(showId: Long): TVShowDetailsResponse

    /**
     * Return the list of episodes for a given series separated by season
     * @param showId Show ID on TVMaze
     * @return A list of episodes for each season
     */
    suspend fun getEpisodesByShowId(showId: Long): List<EpisodeDetailsResponse>

    /**
     * Return detailed info about an episode
     * @param episodeId Episode ID on TVMaze
     * @return [EpisodeDetail] object containing all details about an episode
     */
    suspend fun getEpisodeById(episodeId: Long): EpisodeDetailsResponse
}