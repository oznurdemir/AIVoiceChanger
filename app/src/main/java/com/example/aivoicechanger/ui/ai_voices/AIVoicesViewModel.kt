package com.example.aivoicechanger.ui.ai_voices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivoicechanger.data.entity.generate_voice_ai.celebrity_info.CelebrityItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AIVoicesViewModel @Inject constructor(private val aiVoicesUseCase: AIVoicesUseCase): ViewModel(){
    private val _celebrityItems = MutableStateFlow<List<CelebrityItem>>(emptyList())
    val celebrityItems : Flow<List<CelebrityItem>> = _celebrityItems.asStateFlow()

    init {
        celebrityList()
    }

    private fun celebrityList() {
        viewModelScope.launch {
            aiVoicesUseCase().collect { celebrityItemList ->
                _celebrityItems.value = celebrityItemList
            }
        }
    }
}