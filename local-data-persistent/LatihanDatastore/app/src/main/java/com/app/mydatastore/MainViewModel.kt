package com.app.mydatastore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val pref: SettingPreferences): ViewModel() {
//	Untuk saat ini, karena keluaran dari DataStore masih berupa Flow, maka kita perlu mengubahnya menjadi LiveData pada VIewModel dengan cara seperti berikut:
	fun getThemeSettings(): LiveData<Boolean> {
		return pref.getThemeSetting().asLiveData()
	}

//	Menyimpan data ke preferences
	fun saveThemeSetting(isDarkModeActive: Boolean) {
		viewModelScope.launch {
			pref.saveThemeSetting(isDarkModeActive)
		}
	}
}