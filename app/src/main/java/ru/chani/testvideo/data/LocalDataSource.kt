package ru.chani.testvideo.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.chani.testvideo.domain.entity.Video
import java.io.IOException

class LocalDataSource(private val context: Context) {

    fun getListOfVideos(): List<Video> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("medialist.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

        val listVideoTypeModel = object : TypeToken<List<Video>>() {}.type
        return Gson().fromJson(jsonString, listVideoTypeModel)
    }

}
