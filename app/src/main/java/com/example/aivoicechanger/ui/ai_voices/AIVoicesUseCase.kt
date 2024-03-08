package com.example.aivoicechanger.ui.ai_voices

import kotlinx.coroutines.flow.Flow
import com.example.aivoicechanger.data.entity.generate_voice_ai.celebrity_info.CelebrityItem
import com.example.aivoicechanger.repository.Repository
import javax.inject.Inject

class AIVoicesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): Flow<List<CelebrityItem>> {
        return repository.celebrityList()
    }
}