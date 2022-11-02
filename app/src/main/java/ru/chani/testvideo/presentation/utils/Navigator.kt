package ru.chani.testvideo.presentation.utils

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun playNextVideo()
}