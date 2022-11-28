package br.com.las.data.data

data class TVShowResponse (
    val id: Long,
    val name: String
) {
    var thumbnailUrl: String? = null
        internal set

}