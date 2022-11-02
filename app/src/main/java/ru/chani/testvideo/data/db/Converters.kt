package ru.chani.testvideo.data.db

import androidx.room.TypeConverter
import ru.chani.testvideo.domain.entity.Report
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?) = value?.let { Date(value) }

    @TypeConverter
    fun dateToTimestamp(date: Date?) = date?.time

}