package br.com.las.data.repositories.models

data class EpisodeModel(val id: Long, val name: String) {
    var season : Int = 0
        internal set
    var number : Int = 0
        internal set
    var imageUrl: String? = null
        internal set
    var summary: String? = null
        internal set
    var airDate: String? = null
        internal set
    var airTime: String? = null
        internal set
}