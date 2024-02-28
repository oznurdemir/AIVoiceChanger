package com.example.aivoicechanger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aivoicechanger.data.entity.song.Song

@Database(entities = [Song :: class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val voiceDao : VoiceDao
}