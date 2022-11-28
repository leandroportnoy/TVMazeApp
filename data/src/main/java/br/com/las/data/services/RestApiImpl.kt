package br.com.las.data.services

import android.content.Context
import android.util.Log
import br.com.las.commom.extensions.idToString
import br.com.las.data.R
import br.com.las.data.data.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.json.JSONException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine

internal class RestApiImpl @Inject constructor (@ApplicationContext val appContext: Context) : RestApi {

    companion object {

        const val TAG = "RestApiImpl"

        /** URL to retrieve a list of shows */
        const val SHOW_LIST_GET_URL = "https://api.tvmaze.com/shows?page="

        /** URL to retrieve a specific show */
        const val SHOW_GET_URL = "https://api.tvmaze.com/shows/"

        /** URL to retrieve a specific show */
        const val EPISODES_GET_URL = "https://api.tvmaze.com/episodes/"

        const val EPISODES_GET_PARAMETER = "/episodes"
    }

    override suspend fun getShows(page: Int): List<TVShowDetailsResponse> {
        return suspendCoroutine { continuation ->
            Volley.newRequestQueue(appContext)
                .add(StringRequest(Request.Method.GET, SHOW_LIST_GET_URL + page, { response ->
                    try {
                        val objType = object : TypeToken<List<TVShowDetailsResponse>>() {}.type
                        continuation.resumeWith(Result.success(Gson().fromJson(response, objType)))
                    } catch (exception: JSONException) {
                        Log.e(TAG, R.string.lbl_error_while_retrieve_data.idToString(appContext), exception)
                        continuation.resumeWith(Result.success(emptyList()))
                    }
                }) { volleyError ->
                    Log.e(TAG, R.string.lbl_error_while_setting_volley.idToString(appContext).plus(volleyError))
                    continuation.resumeWith(Result.success(emptyList()))
                })
        }
    }

    override suspend fun getShowById(showId: Long): TVShowDetailsResponse {
        return suspendCoroutine { continuation ->
            Volley.newRequestQueue(appContext)
                .add(StringRequest(Request.Method.GET, SHOW_GET_URL + showId, { response ->
                    try {
                        continuation.resumeWith(Result.success(Gson().fromJson(response, TVShowDetailsResponse::class.java)))
                    } catch (exception: JSONException) {
                        Log.e(TAG, R.string.lbl_error_while_retrieve_data.idToString(appContext), exception)
                        continuation.resumeWith(Result.success(TVShowDetailsResponse(0, "")))
                    }
                }) { volleyError ->
                    Log.e(TAG, R.string.lbl_error_while_setting_volley.idToString(appContext).plus(volleyError))
                    continuation.resumeWith(Result.success(TVShowDetailsResponse(0, "")))
                })
        }
    }

    override suspend fun getEpisodesByShowId(showId: Long): List<EpisodeDetailsResponse> {
        return suspendCoroutine { continuation ->
            Volley.newRequestQueue(appContext)
                .add(StringRequest(Request.Method.GET, SHOW_GET_URL + showId + EPISODES_GET_PARAMETER, { response ->
                    try {
                        val objType = object : TypeToken<List<EpisodeDetailsResponse>>() {}.type
                        val episodeList : List<EpisodeDetailsResponse> = Gson().fromJson(response, objType)
                        continuation.resumeWith(Result.success(episodeList))
                    } catch (exception: JSONException) {
                        Log.e(TAG, R.string.lbl_error_while_retrieve_data.idToString(appContext), exception)
                        continuation.resumeWith(Result.success(emptyList()))
                    }
                }) { volleyError ->
                    Log.e(TAG, R.string.lbl_error_while_setting_volley.idToString(appContext).plus(volleyError))
                    continuation.resumeWith(Result.success(emptyList()))
                })
        }
    }

    override suspend fun getEpisodeById(episodeId: Long): EpisodeDetailsResponse {
        return suspendCoroutine { continuation ->
            Volley.newRequestQueue(appContext)
                .add(StringRequest(Request.Method.GET, EPISODES_GET_URL + episodeId, { response ->
                    try {
                        continuation.resumeWith(Result.success(Gson().fromJson(response, EpisodeDetailsResponse::class.java)))
                    } catch (exception: JSONException) {
                        Log.e(TAG, "Error while retrieving data", exception)
                        continuation.resumeWith(Result.success(EpisodeDetailsResponse(0, "")))
                    }
                }) { volleyError ->
                    Log.e(TAG, "Error while setting up Volley: $volleyError")
                    continuation.resumeWith(Result.success(EpisodeDetailsResponse(0, "")))
                })
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RestAPIProvider {
    @Singleton
    @Binds
    internal abstract fun getTVShowRepository(impl: RestApiImpl): RestApi
}