package ru.chani.testvideo.presentation

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.chani.testvideo.databinding.FragmentVideoBinding
import ru.chani.testvideo.domain.entity.Video
import ru.chani.testvideo.presentation.utils.SurfaceHolderCallback
import ru.chani.testvideo.presentation.utils.SurfaceViewSizer
import ru.chani.testvideo.presentation.utils.navigator


class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding: FragmentVideoBinding
        get() = _binding ?: throw RuntimeException("FragmentVideoBinding == null")

    lateinit var currentVideo: Video
    lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        savedInstanceState?.let {
            currentVideo = savedInstanceState.getParcelable(KEY_CURRENT_VIDEO)
                ?: throw RuntimeException("Video == null")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(layoutInflater)


        mediaPlayer = MediaPlayer()


        val afd = requireContext().assets.openFd(currentVideo.nameOfFile)
        val callBack = SurfaceHolderCallback(
            navigator = navigator(),
            mediaPlayer = mediaPlayer,
            afd = afd,
            changeScreenSizeByVideo = ::setRightVideoSize
        )

        binding.sv.holder.addCallback(callBack)

        return binding.root
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(KEY_CURRENT_VIDEO, currentVideo)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun setRightVideoSize(videoWidth: Int, videoHeight: Int) {
        binding.sv.layoutParams = SurfaceViewSizer.getLayoutParamsByVideo(
            activity = requireActivity(),
            surfaceView = binding.sv,
            videoWidth = videoWidth,
            videoHeight = videoHeight
        )
    }

    companion object {
        fun newInstance(video: Video): VideoFragment {
            return VideoFragment().apply { currentVideo = video }
        }

        private const val KEY_CURRENT_VIDEO = "KEY_CURRENT_VIDEO"
    }
}