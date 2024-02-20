package com.example.aivoicechanger.data.entity.generate_voice_ai

data class VoiceRequest (
    val uuid_idempotency_token : String,
    val tts_model_token : String,
    val inference_text : String
)