package com.example.aivoicechanger.repository

import com.example.aivoicechanger.data.datasource.DataSource
import kotlinx.coroutines.flow.Flow

class Repository (private val dataSource: DataSource) {
    suspend fun settingsList() : Flow<List<Int>> = dataSource.settingsList()
}