package br.com.las.data.data

import com.google.gson.annotations.SerializedName

data class EpisodeDetailsResponse (val id: Long, val name: String) {
    var season: Int = 0
    var number: Int = 0
    @SerializedName("image")
    var images: Image? = null
    var summary: String? = null
    @SerializedName("airdate")
    var airDate: String? = null
    @SerializedName("airtime")
    var airTime: String? = null

}