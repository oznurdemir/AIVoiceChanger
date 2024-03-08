package com.example.aivoicechanger.ui.song_playing

import com.example.aivoicechanger.data.entity.song.Song
import com.example.aivoicechanger.repository.Repository
import javax.inject.Inject

class SongPlayingUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(voice: Song) {
        repository.addVoice(voice)
    }
}