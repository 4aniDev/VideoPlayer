package ru.chani.testvideo.data.db

import ru.chani.testvideo.domain.entity.Report

class ReportsMapper {

    fun mapReportToReportDbModel(report: Report): ReportDbModel {
        return ReportDbModel(
            id = report.id,
            id_video = report.id_video,
            video_name = report.video_name,
            startTime = report.startTime
        )
    }

    fun mapReportDbModelToReport(report: ReportDbModel): Report {
        return Report(
            id = report.id,
            id_video = report.id_video,
            video_name = report.video_name,
            startTime = report.startTime
        )
    }
}