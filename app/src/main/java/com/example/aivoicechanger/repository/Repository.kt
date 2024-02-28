package com.example.aivoicechanger.repository

import com.example.aivoicechanger.data.datasource.DataSource
import com.example.aivoicechanger.data.entity.generate_voice_ai.celebrity_info.CelebrityItem
import com.example.aivoicechanger.data.entity.generate_voice_ai.music.MusicResponse
import com.example.aivoicechanger.data.entity.song.Song
import kotlinx.coroutines.flow.Flow

class Repository (private val dataSource: DataSource) {
    suspend fun settingsList() : Flow<List<Int>> = dataSource.settingsList()
    suspend fun celebrityList() : Flow<List<CelebrityItem>> = dataSource.celebrityList()
    suspend fun getMusicUrl(musicToken : String) : Flow<MusicResponse> = dataSource.getMusicUrl(musicToken)
    suspend fun addVoice(voice : Song) = dataSource.addVoice(voice)
}