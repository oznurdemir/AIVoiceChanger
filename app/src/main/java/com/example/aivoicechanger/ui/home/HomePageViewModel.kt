package com.example.aivoicechanger.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivoicechanger.data.entity.song.Song
import com.example.aivoicechanger.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(val repository: Repository) : ViewModel(){
    private val _voiceCount = MutableStateFlow(0)
    val voiceCount : Flow<Int> = _voiceCount.asStateFlow()

    init {
        getVoice()
        getVoiceCount()
    }

    fun getVoice() = repository.getVoice()

    private fun getVoiceCount() {
        viewModelScope.launch {
            _voiceCount.value = repository.getVoiceCount()
        }
    }

    fun deleteVoice(voice : Song) {
        viewModelScope.launch {
            repository.deleteVoice(voice)
        }
    }
}