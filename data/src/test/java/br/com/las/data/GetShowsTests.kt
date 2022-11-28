package br.com.las.data

import br.com.las.data.data.TVShowDetailsResponse
import br.com.las.data.repositories.TVMazeRepositoryImpl
import br.com.las.data.services.RestApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetShowsTests {

    @Test
    fun testGetShows_EP01() = runBlocking {
        val restApi = mock<RestApi> {
            on { runBlocking { getShows(1) } } doReturn listOf(TVShowDetailsResponse(1, "tv show test").apply {
                this.images = null
            })
        }
        val result = TVMazeRepositoryImpl(restApi).getShows(1)
        Assert.assertNotNull(result)
    }

    @Test
    fun testGetShows_EmptyList() = runBlocking {
        val restApi = mock<RestApi> {
            on { runBlocking { getShows(1) } } doReturn emptyList()
        }
        val result = restApi.getShows(1)
        assert(result.isEmpty())

    }

}