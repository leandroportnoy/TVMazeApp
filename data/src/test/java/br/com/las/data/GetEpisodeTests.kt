package br.com.las.data

import br.com.las.data.data.EpisodeDetailsResponse
import br.com.las.data.repositories.TVMazeRepositoryImpl
import br.com.las.data.services.RestApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetEpisodeTests {

    @Test
    fun testGetEpisode_EP01() = runBlocking {
        val restApi = mock<RestApi> {
            on { runBlocking { getEpisodeById(1) } } doReturn EpisodeDetailsResponse(1, "my test")
        }
        val result = TVMazeRepositoryImpl(restApi).getEpisodeById(1)
        assertNotNull(result)

    }
}