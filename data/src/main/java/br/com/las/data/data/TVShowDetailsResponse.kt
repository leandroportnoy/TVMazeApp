package br.com.las.data.data

import com.google.gson.annotations.SerializedName

data class TVShowDetailsResponse(
    val id: Long,
    val name: String
) {
    @SerializedName("image")
    var images: Image? = null
    var summary: String? = null
//        get() { return field?.stripHtmlOut() }
    var status: String? = null
    var type: String? = null
    var genres: List<String>? = null
    var premiered: String? = null
    var ended: String? = null
    var officialSite: String? = null
    var runtime: Int = 0
    var averageRuntime: Int = 0
    var weight: Int = 0
    var rating: Rating? = null
    var schedule: Schedule? = null
    var network: Network? = null
}