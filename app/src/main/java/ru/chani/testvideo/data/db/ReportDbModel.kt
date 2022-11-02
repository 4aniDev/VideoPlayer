package ru.chani.testvideo.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reports")
data class ReportDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val id_video: Int,
    val video_name: String,
    val startTime: Date
)