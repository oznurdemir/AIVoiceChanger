package com.example.aivoicechanger.data.entity.song_playing

import java.io.Serializable

data class SongPlayingData (
    val image : Int,
    val name : Int,
    val musicPath : String,
    val text : String
) : Serializable