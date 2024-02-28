package com.example.aivoicechanger.data.entity.song

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("song")
data class Song (
    @PrimaryKey(autoGenerate = true)
    private val id : Int,
    private val celebrityImage : Int,
    private val celebrityName : Int,
    private val text : String
)