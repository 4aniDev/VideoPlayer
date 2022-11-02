package ru.chani.testvideo.domain.usecase

import ru.chani.testvideo.domain.entity.Video

class GetListOfVideos(private val repository: Repository) {

    operator fun invoke(): List<Video> {
        return repository.getListOfVideos()
    }

}