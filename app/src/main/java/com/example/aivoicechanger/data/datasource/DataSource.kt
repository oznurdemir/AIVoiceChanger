package com.example.aivoicechanger.data.datasource

import com.example.aivoicechanger.R
import com.example.aivoicechanger.data.database.Database
import com.example.aivoicechanger.data.entity.generate_voice_ai.celebrity_info.CelebrityItem
import com.example.aivoicechanger.data.entity.generate_voice_ai.music.MusicResponse
import com.example.aivoicechanger.data.entity.song.Song
import com.example.aivoicechanger.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataSource (val apiService: ApiService, val voiceDatabase: Database){
    suspend fun settingsList() : Flow<List<Int>> = flow {
        val settingsItems = ArrayList<Int>()
        settingsItems.add(R.string.share_app)
        settingsItems.add(R.string.rate_us)
        settingsItems.add(R.string.contact_us)
        settingsItems.add(R.string.terms_of_service)
        settingsItems.add(R.string.privacy_policy)
        emit(settingsItems)
    }.flowOn(Dispatchers.IO)

    suspend fun celebrityList() : Flow<List<CelebrityItem>> = flow {
        val celebrityItemList: ArrayList<CelebrityItem> = arrayListOf(
            CelebrityItem(R.drawable.trump, R.string.trump, R.string.trump_token,""),
            CelebrityItem(R.drawable.obama, R.string.obama, R.string.obama_token,""),
            CelebrityItem(R.drawable.tylor, R.string.taylor, R.string.taylor_token,""),
            CelebrityItem(R.drawable.elon, R.string.elon, R.string.elon_token,""),
            CelebrityItem(R.drawable.brad_pitt, R.string.brad, R.string.brad_token,""),
            CelebrityItem(R.drawable.rapper, R.string.cent_50, R.string.cent_50_token,""),
            CelebrityItem(R.drawable.dua_lipa, R.string.dua_lipa, R.string.dua_lipa_token,""),
            CelebrityItem(R.drawable.johny, R.string.johnny, R.string.johnny_token,"")
        )
        emit(celebrityItemList)
    }.flowOn(Dispatchers.IO)

    suspend fun getMusicUrl(musicToken : String) : Flow<MusicResponse> = flow {
        emit(apiService.getMusicUrl(musicToken))
    }.flowOn(Dispatchers.IO)

    suspend fun addVoice(voice : Song) {
        voiceDatabase.voiceDao.addVoice(voice)
    }
}