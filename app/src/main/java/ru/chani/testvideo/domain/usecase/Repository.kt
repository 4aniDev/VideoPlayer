package ru.chani.testvideo.domain.usecase

import ru.chani.testvideo.domain.entity.Report
import ru.chani.testvideo.domain.entity.Video

interface Repository {

    fun getListOfVideos(): List<Video>

    suspend fun addReportRecord(report: Report)

}
