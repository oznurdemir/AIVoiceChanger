package com.example.aivoicechanger.ui.setting

import com.example.aivoicechanger.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(): Flow<List<Int>> {
        return repository.settingsList()
    }
}