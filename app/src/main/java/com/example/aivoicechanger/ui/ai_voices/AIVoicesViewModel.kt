package com.example.aivoicechanger.ui.ai_voices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aivoicechanger.data.entity.generate_voice_ai.CelebrityItem
import com.example.aivoicechanger.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AIVoicesViewModel @Inject constructor(val repository: Repository): ViewModel(){
    private val _celebrityItems = MutableStateFlow<List<CelebrityItem>>(emptyList())
    val celebrityItems : Flow<List<CelebrityItem>> = _celebrityItems.asStateFlow()

    init {
        celebrityList()
    }

    private fun celebrityList() {
        viewModelScope.launch {
            repository.celebrityList().collect{celebrityItemList->
                _celebrityItems.value = celebrityItemList
            }
        }
    }
}