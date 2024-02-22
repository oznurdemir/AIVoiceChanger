package com.example.aivoicechanger.data.entity.generate_voice_ai.music


import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("attempt_count")
    val attemptCount: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("job_token")
    val jobToken: String?,
    @SerializedName("maybe_extra_status_description")
    val maybeExtraStatusDescription: Any?,
    @SerializedName("maybe_public_bucket_wav_audio_path")
    val maybePublicBucketWavAudioPath: String?,
    @SerializedName("maybe_result_token")
    val maybeResultToken: String?,
    @SerializedName("model_token")
    val modelToken: String?,
    @SerializedName("raw_inference_text")
    val rawİnferenceText: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("tts_model_type")
    val ttsModelType: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)