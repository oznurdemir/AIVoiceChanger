package com.example.aivoicechanger.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.aivoicechanger.data.entity.song.Song

@Dao
interface VoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVoice (voiceEntity : Song)
}