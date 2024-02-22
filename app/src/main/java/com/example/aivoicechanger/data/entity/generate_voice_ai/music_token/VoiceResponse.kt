package com.example.aivoicechanger.data.entity.generate_voice_ai.music_token


import com.google.gson.annotations.SerializedName

data class VoiceResponse(
    @SerializedName("inference_job_token")
    val inferenceJobToken: String?,
    @SerializedName("inference_job_token_type")
    val inferenceJobTokenType: String?,
    @SerializedName("success")
    val success: Boolean?
)