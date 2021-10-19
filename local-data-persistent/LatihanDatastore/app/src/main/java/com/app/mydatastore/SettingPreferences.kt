package com.app.mydatastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>) {

	private val THEME_KEY = booleanPreferencesKey("theme_setting")

//	Perlu diperhatikan juga bahwa nilai kembalian dari fungsi ini berupa Flow.
//	Flow merupakan salah satu bagian dari Coroutine yang digunakan untuk mengambil data secara berkelanjutan (data stream) dengan jumlah yang banyak.
	fun getThemeSetting(): Flow<Boolean> {
		return dataStore.data.map { preferences ->
			preferences[THEME_KEY] ?: false
		}
	}

//	Suspend function harus dijalankan di corotine/suspend fun lain
	suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
		dataStore.edit { preferences ->
			preferences[THEME_KEY] = isDarkModeActive
		}
	}

	companion object {
//		Instance dari datastore
//		Volatile adalah keyword yang digunakan supaya nilai pada suatu variabel tidak dimasukkan ke dalam cache. Kemudian jika Activity B memanggil fungsi ini,
//		kelas tersebut akan memeriksa apakah instance-nya sudah ada. Jika tidak null, sistem akan mengembalikan instance tersebut pada Activity B, tidak membuat instance baru.
		@Volatile
		private var INSTANCE: SettingPreferences? = null

		fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
			return INSTANCE ?: synchronized(this) {
				val instance = SettingPreferences(dataStore)
				INSTANCE = instance
				instance
			}
		}
	}
}