package com.example.aivoicechanger.repository

import com.example.aivoicechanger.data.datasource.DataSource
import com.example.aivoicechanger.data.entity.generate_voice_ai.CelebrityItem
import kotlinx.coroutines.flow.Flow

class Repository (private val dataSource: DataSource) {
    suspend fun settingsList() : Flow<List<Int>> = dataSource.settingsList()
    suspend fun celebrityList() : Flow<List<CelebrityItem>> = dataSource.celebrityList()
}