package com.example.aivoicechanger.data.entity.generate_voice_ai.music


import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("state")
    val state: State?,
    @SerializedName("success")
    val success: Boolean?
)