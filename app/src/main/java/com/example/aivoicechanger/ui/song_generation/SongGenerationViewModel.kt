package com.example.aivoicechanger.ui.song_generation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivoicechanger.data.entity.generate_voice_ai.VoiceRequest
import com.example.aivoicechanger.data.entity.generate_voice_ai.VoiceResponse
import com.example.aivoicechanger.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongGenerationViewModel @Inject constructor(): ViewModel() {
    private val _voice = MutableStateFlow<VoiceResponse?>(null)
    val voice: StateFlow<VoiceResponse?> = _voice.asStateFlow()

    fun postVoice(token: VoiceRequest) {
        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().sendToken(token)
                _voice.value = response
            } catch (e: Exception) {
                Log.e("error", e.localizedMessage)
            }
        }
    }
}