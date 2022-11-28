package br.com.las.data.data

data class EpisodeResponse(val id: Long, val name: String) {
    var season: Int = 0
    var number: Int = 0
}