package com.example.aivoicechanger.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aivoicechanger.data.entity.song.Song

@Dao
interface VoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVoice (voiceEntity : Song)

    @Query("SELECT * FROM song ORDER BY id DESC")
    fun getVoice() : LiveData<List<Song>>

    @Query("SELECT COUNT(*) FROM song")
    suspend fun getVoiceCount() : Int

    @Query("SELECT * FROM song WHERE celebrityName = :name AND text = :text LIMIT 1")
    suspend fun getVoiceByNameAndText(name: Int, text: String): Song?

}