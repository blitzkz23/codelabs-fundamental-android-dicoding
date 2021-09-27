package com.app.mybroadcastreceiver

import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class DownloadService : JobIntentService() {

	companion object {
		val TAG: String = DownloadService::class.java.simpleName
	}

//	Saat perintah start dijalankan
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		if (intent != null) {
			enqueueWork(this, this::class.java, 101, intent)
		}
		return super.onStartCommand(intent, flags, startId)
	}

//	Menjalankan intent service dengan melakukan unduh file secara asinkronus di background (pura-pura), dimana aslinya hanya tidur 5 detik.
	override fun onHandleWork(intent: Intent) {
		Log.d(TAG, "Download Service dijalankan")
		try {
			Thread.sleep(5000)
		} catch (e:InterruptedException) {
			e.printStackTrace()
		}

//	Ketika pengunduhan selesai service akan membroadcast seuah event yang kemudian direspon activity
		val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
		sendBroadcast(notifyFinishIntent)
	}
}