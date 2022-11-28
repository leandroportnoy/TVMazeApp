package br.com.las.data

import br.com.las.data.data.TVShowDetailsResponse
import br.com.las.data.services.RestApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetShowTests {

    @Test
    fun testGetShow_S01() = runBlocking {
        val restApi = mock<RestApi> {
            on {
                runBlocking { getShowById(1) }
            } doReturn TVShowDetailsResponse(1, "my show test")
        }
        val result = restApi.getShowById(1)
        Assert.assertNotNull(result)
    }
}