package com.app.mydatastore

import android.content.Context
import android.media.MediaCodec.MetricsConstants.MODE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial

//Membuat extension function dengan property delegation by preferencesDataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val switchTheme: SwitchMaterial = findViewById(R.id.switch_theme)

//		Inisialisasi preferences dan viewmodel terlebih dahulu
		val pref = SettingPreferences.getInstance(dataStore)

//		ViewModelFactory pada ViewModelProvider digunakan untuk memasukkan constructor ke ViewModel() karena pada ViewModel() tidak bisa inisialisasi secara langsung
//		Dengan ViewModelFactory, Anda dapat memasukkan constructor dengan cara mengirim data ke VIewModelFactory terlebih dahulu, baru setelah itu dikirimkan ke ViewModel pada fungsi create.
		val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
			MainViewModel::class.java
		)

		mainViewModel.getThemeSettings().observe(this,
			{ isDarkModeActive: Boolean ->
				if (isDarkModeActive) {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
					switchTheme.isChecked = true
				} else {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
					switchTheme.isChecked = false
				}
			})


		switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
			mainViewModel.saveThemeSetting(isChecked)
		}
	}
}