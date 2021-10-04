package com.app.mylivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.mylivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private lateinit var activityMainBinding: ActivityMainBinding
	private lateinit var mLiveDataTimerViewModel: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(activityMainBinding.root)

//		Instance view model
		mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
		
//		This function is for subsribing to the observer
		subscribe()
	}

	private fun subscribe() {
		val elapsedTimeObserver = Observer<Long?> {  aLong ->
			val newText = this@MainActivity.resources.getString(R.string.seconds, aLong)
			activityMainBinding.timerTextview.text = newText
		}

		mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
	}
}