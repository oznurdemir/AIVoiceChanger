package com.example.aivoicechanger.ui.song_playing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivoicechanger.data.entity.song.Song
import com.example.aivoicechanger.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongPlayingViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    fun addVoice(voice: Song) = viewModelScope.launch {
        repository.addVoice(voice)
    }
}