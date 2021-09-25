package com.app.myservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button

class MainActivity : AppCompatActivity() {
	//		Variable for bound service connection
	private var mServiceBound = false
	private lateinit var mBoundService: MyBoundService

	private val mServiceConnection = object : ServiceConnection {
		override fun onServiceDisconnected(name: ComponentName?) {
			mServiceBound = false
		}

		override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
			val myBinder = service as MyBoundService.MyBinder
			mBoundService = myBinder.getService
			mServiceBound = true
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val btnStartService: Button = findViewById(R.id.btn_start_service)
		val btnStartJobService: Button = findViewById(R.id.btn_start_job_intent_service)
		val btnStartBoundService: Button = findViewById(R.id.btn_start_bound_service)
		val btnStopBoundService: Button = findViewById(R.id.btn_stop_bound_service)

		btnStartService.setOnClickListener {
			val mStartServiceIntent = Intent(this, MyService::class.java)
			startService(mStartServiceIntent)
		}
		btnStartJobService.setOnClickListener {
			val mStartJobIntentService = Intent(this,  MyJobIntentService::class.java)
			mStartJobIntentService.putExtra(MyJobIntentService.EXTRA_DURATION, 5000L)
			MyJobIntentService.enqueueWork(this, mStartJobIntentService)
		}
		btnStartBoundService.setOnClickListener {
			val mBoundServiceIntent = Intent(this, MyBoundService::class.java)
			bindService(mBoundServiceIntent, mServiceConnection, BIND_AUTO_CREATE)
		}
		btnStopBoundService.setOnClickListener {
			unbindService(mServiceConnection)
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		if(mServiceBound) {
			unbindService(mServiceConnection)
		}
	}
}