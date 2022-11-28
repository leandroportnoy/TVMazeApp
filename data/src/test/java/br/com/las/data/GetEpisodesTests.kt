package br.com.las.data

import br.com.las.data.data.EpisodeDetailsResponse
import br.com.las.data.services.RestApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetEpisodesTests {

    @Test
    fun testGetEpisodes_EPS() = runBlocking {
        val restApi = mock<RestApi> {
            on { runBlocking { getEpisodesByShowId(1) } } doReturn listOf(EpisodeDetailsResponse(1, "my test").apply {
                this.season = 2
                this.images = null
                this.summary = "summary test"
            })
        }
        val result = restApi.getEpisodesByShowId(1)
        Assert.assertNotNull(result)
    }

    @Test
    fun testGetEpisodes_EmptyList() = runBlocking {
        val restApi = mock<RestApi> {
            on { runBlocking { getEpisodesByShowId(1) } } doReturn emptyList()
        }
        val result = restApi.getEpisodesByShowId(1)
        assert(result.isEmpty())

    }
}