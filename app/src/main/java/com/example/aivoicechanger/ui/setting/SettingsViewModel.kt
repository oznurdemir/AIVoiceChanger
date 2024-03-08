package com.example.aivoicechanger.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsUseCase: SettingsUseCase) : ViewModel() {
    private val _settingsItem = MutableStateFlow<List<Int>>(emptyList())
    val settingsItem : Flow<List<Int>> = _settingsItem.asStateFlow()

    init {
        getSettingsItem()
    }

    private fun getSettingsItem() {
        viewModelScope.launch {
            settingsUseCase().collect{ itemsList ->
                _settingsItem.value = itemsList
            }
        }
    }
}