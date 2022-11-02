package ru.chani.testvideo.domain.usecase

import ru.chani.testvideo.domain.entity.Report
import ru.chani.testvideo.domain.entity.Video
import java.util.Date

class AddVideoPlayReport(private val repository: Repository) {
    suspend operator fun invoke(video: Video) {
        val report = Report(
            id_video = video.videoId,
            video_name = video.nameOfFile,
            startTime = Date()
        )
        repository.addReportRecord(report = report)
    }
}