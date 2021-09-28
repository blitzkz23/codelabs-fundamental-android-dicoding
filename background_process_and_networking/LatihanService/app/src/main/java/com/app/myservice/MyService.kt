package com.app.myservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.lang.UnsupportedOperationException

class MyService : Service() {

	companion object {
		internal val TAG = MyService::class.java.simpleName
	}

	private val serviceJob = Job()
	private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

	override fun onBind(intent: Intent): IBinder {
		throw UnsupportedOperationException("Not yet implemented")
	}

//	After the listener caught the start command this function will run
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Log.d(TAG, "Service dijalankan...");
		serviceScope.launch {
			delay(3000)
//			Stop self to stop and shutdown the MyService from android system
			stopSelf()
			Log.d(TAG, "Service dihentikan")
		}
//		START_STICKY tags if a service is shut down by android system because lack of memory, and will be created again if there's more memories.
		return START_STICKY
	}

	override fun onDestroy() {
		super.onDestroy()
		serviceJob.cancel()
		Log.d(TAG, "onDestroy:")
	}
}