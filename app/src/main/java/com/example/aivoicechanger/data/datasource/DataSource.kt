package com.example.aivoicechanger.data.datasource

import com.example.aivoicechanger.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataSource (){
    suspend fun settingsList() : Flow<List<Int>> = flow {
        val settingsItems = ArrayList<Int>()
        settingsItems.add(R.string.share_app)
        settingsItems.add(R.string.rate_us)
        settingsItems.add(R.string.contact_us)
        settingsItems.add(R.string.terms_of_service)
        settingsItems.add(R.string.privacy_policy)
        emit(settingsItems)
    }.flowOn(Dispatchers.IO)
}