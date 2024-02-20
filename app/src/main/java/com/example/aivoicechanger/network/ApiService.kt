package com.example.aivoicechanger.network

import com.example.aivoicechanger.data.entity.generate_voice_ai.VoiceRequest
import com.example.aivoicechanger.data.entity.generate_voice_ai.VoiceResponse
import com.example.aivoicechanger.utils.Constants
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("${Constants.END_POINT_VOICE}")
    suspend fun sendToken(@Body token : VoiceRequest) : VoiceResponse
}