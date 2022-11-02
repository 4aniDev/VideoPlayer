package ru.chani.testvideo.domain.entity

import java.util.*

data class Report(
    var id: Int = UNDEFINED_ID,
    val id_video: Int,
    val video_name: String,
    val startTime: Date

) {
    companion object {
        private const val UNDEFINED_ID = 0
    }
}

