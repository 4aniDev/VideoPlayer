package ru.chani.testvideo.presentation.utils

import android.app.Activity
import android.view.SurfaceView
import android.view.ViewGroup.LayoutParams

object SurfaceViewSizer {
    fun getLayoutParamsByVideo (activity: Activity, surfaceView: SurfaceView, videoWidth: Int, videoHeight: Int): LayoutParams {
        val videoProportion = videoWidth.toFloat() / videoHeight.toFloat()

        val screenWidth: Int = activity.windowManager.defaultDisplay.width
        val screenHeight: Int = activity.windowManager.defaultDisplay.height
        val screenProportion = screenWidth.toFloat() / screenHeight.toFloat()

        val lp = surfaceView.layoutParams
        if (videoProportion > screenProportion) {
            lp.width = screenWidth
            lp.height = (screenWidth.toFloat() / videoProportion).toInt()
        } else {
            lp.width = (videoProportion * screenHeight.toFloat()).toInt()
            lp.height = screenHeight
        }

        return lp
    }
}