package com.example.aivoicechanger.data.entity.generate_voice_ai.music_token


import com.google.gson.annotations.SerializedName

data class VoiceRequest(
    @SerializedName("inference_text")
    val inferenceText: String?,
    @SerializedName("tts_model_token")
    val ttsModelToken: String?,
    @SerializedName("uuid_idempotency_token")
    val uuidIdempotencyToken: String?
)