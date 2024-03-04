package com.example.aivoicechanger.data.entity.song

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("song")
data class Song (
    @PrimaryKey(autoGenerate = true)
     val id : Int,
     val celebrityImage : Int,
     val celebrityName : Int,
     val text : String,
    val url : String
)