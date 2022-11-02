package ru.chani.testvideo.data

import android.app.Application
import ru.chani.testvideo.data.db.AppDatabase
import ru.chani.testvideo.data.db.ReportsMapper
import ru.chani.testvideo.domain.entity.Report
import ru.chani.testvideo.domain.entity.Video
import ru.chani.testvideo.domain.usecase.Repository

class RepositoryImp(private val application: Application): Repository {

    private val localDataSource = LocalDataSource(application)
    private val reportsDao = AppDatabase.getInstance(application).reportDao()
    private val mapper = ReportsMapper()

    override fun getListOfVideos(): List<Video> {
        return localDataSource.getListOfVideos()
    }

    override suspend fun addReportRecord(report: Report) {
        val reportDbModel = mapper.mapReportToReportDbModel(report)
        reportsDao.addReport(reportDbModel)
    }
}