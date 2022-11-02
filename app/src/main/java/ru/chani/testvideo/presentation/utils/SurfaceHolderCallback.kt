package ru.chani.testvideo.presentation.utils

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.SurfaceHolder

class SurfaceHolderCallback(
    private val navigator: Navigator,
    private val mediaPlayer: MediaPlayer,
    private val afd: AssetFileDescriptor,
    val changeScreenSizeByVideo: (videoWith: Int, videoHeight: Int) -> Unit
) : SurfaceHolder.Callback {
    override fun surfaceCreated(holder: SurfaceHolder) {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            mediaPlayer.prepare()
        }
        mediaPlayer.setOnPreparedListener {
            changeScreenSizeByVideo(mediaPlayer.videoWidth, mediaPlayer.videoHeight)
            it.start()
        }
        mediaPlayer.setOnCompletionListener { navigator.playNextVideo() }
        mediaPlayer.setDisplay(holder)
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .build()
        )
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}

    override fun surfaceDestroyed(p0: SurfaceHolder) {}

}