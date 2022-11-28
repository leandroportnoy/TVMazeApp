package br.com.las.data.repositories

import androidx.annotation.WorkerThread
import br.com.las.data.repositories.models.EpisodeModel
import br.com.las.data.repositories.models.TVShowModel

sealed interface TVMazeRepository {

    @WorkerThread
    suspend fun getShows(page: Int): List<TVShowModel>

    @WorkerThread
    suspend fun getShowById(id: Long): TVShowModel

    @WorkerThread
    suspend fun getEpisodes(showId: Long): List<EpisodeModel>

    @WorkerThread
    suspend fun getEpisodeById(episodeId: Long): EpisodeModel

}