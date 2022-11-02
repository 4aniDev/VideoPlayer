package ru.chani.testvideo.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    @SerializedName("VideoId")
    var videoId: Int,
    @SerializedName("VideoIdentifier")
    var nameOfFile: String,
    @SerializedName("OrderNumber")
    var orderNumber: Int
): Parcelable