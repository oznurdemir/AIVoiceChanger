package com.example.aivoicechanger.ui.song_generation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivoicechanger.data.entity.generate_voice_ai.music_token.VoiceRequest
import com.example.aivoicechanger.data.entity.generate_voice_ai.music_token.VoiceResponse
import com.example.aivoicechanger.network.ApiClient
import com.example.aivoicechanger.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongGenerationViewModel @Inject constructor(val repository: Repository): ViewModel() {
    private val _voice = MutableStateFlow<VoiceResponse?>(null)
    val voice: StateFlow<VoiceResponse?> = _voice.asStateFlow()
    private val _music = MutableStateFlow<String?>(null)
    val music: StateFlow<String?> = _music.asStateFlow()


    fun postVoice(token: VoiceRequest) {
        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().sendToken(token)
                _voice.value = response
            } catch (e: Exception) {
                e.localizedMessage?.let { Log.e("error", it) }
            }
        }
    }

    fun getMusicUrl(token: String) = viewModelScope.launch {
        while (true) {
            try {
                // Music URL'ini al
                val musicUrl = repository.getMusicUrl(token).first()
                // Music URL null değilse, işlem tamamlandı demektir
                musicUrl.state?.let {
                    val url = musicUrl.state.maybePublicBucketWavAudioPath
                    if (url != null) {
                        _music.value = url
                        return@launch // Döngüyü sonlandır
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // Belirli bir süre bekle
            delay(300L)
        }
    }
}