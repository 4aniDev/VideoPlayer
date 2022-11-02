package ru.chani.testvideo.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.chani.testvideo.data.RepositoryImp
import ru.chani.testvideo.domain.usecase.AddVideoPlayReport
import ru.chani.testvideo.domain.usecase.GetListOfVideos

class MainViewModel(application: Application) : ViewModel() {

    private val repository by lazy { RepositoryImp(application = application) }
    private val getListOfVideos by lazy { GetListOfVideos(repository = repository) }

    private val addVideoPlayReport = AddVideoPlayReport(repository)


    private val _currentVideoFragmentForPlaying = MutableLiveData<VideoFragment>()
    val currentVideoFragmentForPlaying: LiveData<VideoFragment>
        get() = _currentVideoFragmentForPlaying

    private var currentVideoIndex = DEFAULT_INDEX_VIDEO_PLAYING

    private val listOfVideos = getListOfVideos().sortedBy { it.orderNumber }
    private val listOfVideoFragments = mutableListOf<VideoFragment>()


    init {
        listOfVideos.forEach { video ->
            listOfVideoFragments.add(VideoFragment.newInstance(video))
        }
        setCurrentFragment()
    }


    fun playNextVideo() {
        if (++currentVideoIndex >= listOfVideos.size) {
            currentVideoIndex = DEFAULT_INDEX_VIDEO_PLAYING
        }
        setCurrentFragment()
    }

    private fun setCurrentFragment() {
        _currentVideoFragmentForPlaying.value = listOfVideoFragments[currentVideoIndex]
        writeReport()
    }

    private fun writeReport() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentVideo = listOfVideos[currentVideoIndex]
            addVideoPlayReport(video = currentVideo)
        }
    }


    companion object {
        private const val DEFAULT_INDEX_VIDEO_PLAYING = 0
    }

}