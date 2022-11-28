package br.com.las.data.repositories.models

import br.com.las.data.repositories.enum.ShowStatus

data class TVShowModel(val id: Long, val name: String) {
    var thumbnailUrl: String? = null
        internal set
    var imageUrl: String? = null
        internal set
    var summary: String? = null
        internal set
    var status: ShowStatus = ShowStatus.UNKNOWN
        internal set
    var type: String? = null
        internal set
    var genres: List<String>? = null
        internal set
    var premiered: String? = null
        internal set
    var ended: String? = null
        internal set
    var schedule: ScheduleModel? = null
        internal set
    var rating: Float = 0f
        internal set
}