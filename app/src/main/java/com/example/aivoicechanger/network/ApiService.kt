package com.example.aivoicechanger.network

import com.example.aivoicechanger.data.entity.generate_voice_ai.music.MusicResponse
import com.example.aivoicechanger.data.entity.generate_voice_ai.music_token.VoiceRequest
import com.example.aivoicechanger.data.entity.generate_voice_ai.music_token.VoiceResponse
import com.example.aivoicechanger.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(Constants.END_POINT_VOICE)
    suspend fun sendToken(@Body token : VoiceRequest) : Response<VoiceResponse>

    @Headers("Accept: application/json")
    @GET(Constants.END_POINT_MUSIC)
    suspend fun getMusicUrl( @Path("inference_job_token") token: String) : MusicResponse
}