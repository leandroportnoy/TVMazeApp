package br.com.las.data.repositories

import androidx.annotation.WorkerThread
import br.com.las.data.repositories.enum.ShowStatus
import br.com.las.data.repositories.models.EpisodeModel
import br.com.las.data.repositories.models.ScheduleModel
import br.com.las.data.repositories.models.TVShowModel
import br.com.las.data.services.RestApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

internal class TVMazeRepositoryImpl @Inject constructor(private val restApi: RestApi): TVMazeRepository {

    @WorkerThread
    override suspend fun getShows(page: Int): List<TVShowModel> =
        withContext(Dispatchers.IO) {
            restApi.getShows(page).map {
                TVShowModel(it.id, it.name).apply {
                    this.thumbnailUrl = it.images?.original
                }
            }
        }

    @WorkerThread
    override suspend fun getShowById(id: Long): TVShowModel =
        withContext(Dispatchers.IO) {
            val result = restApi.getShowById(id)
            TVShowModel(result.id, result.name).apply {
                imageUrl        = result.images?.original
                thumbnailUrl    = result.images?.medium
                summary         = result.summary
                status          = ShowStatus.valueOf(result.status?.uppercase() ?: ShowStatus.UNKNOWN.name)
                type            = result.type
                premiered       = result.premiered
                genres          = result.genres
                ended           = result.ended
                rating          = result.rating?.average ?: 0f
                result.schedule?.let {
                    schedule = ScheduleModel(it.time, it.days)
                }
            }
        }

    @WorkerThread
    override suspend fun getEpisodes(showId: Long): List<EpisodeModel> = withContext(Dispatchers.IO) {
        restApi.getEpisodesByShowId(showId).map {
            EpisodeModel(it.id, it.name). apply {
                season = it.season
                number = it.number
                imageUrl = it.images?.medium
            }
        }
    }

    @WorkerThread
    override suspend fun getEpisodeById(episodeId: Long): EpisodeModel = withContext(Dispatchers.IO) {
        val result = restApi.getEpisodeById(episodeId)
        EpisodeModel(result.id, result.name).apply {
            this.airDate = result.airDate
            this.airTime = result.airTime
            this.season = result.season
            this.summary = result.summary
            this.imageUrl = result.images?.medium
            this.number = result.number
        }
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class TVMazeRepositoryProvider {
    @Singleton
    @Binds
    internal abstract fun getTVMazeRepository(impl: TVMazeRepositoryImpl): TVMazeRepository
}