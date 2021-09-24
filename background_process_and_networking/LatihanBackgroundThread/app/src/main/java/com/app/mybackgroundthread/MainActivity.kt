package com.app.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val btnStart: Button = findViewById(R.id.btn_start)
		val tvStatus: TextView = findViewById(R.id.tv_status)

		btnStart.setOnClickListener {
			try {
//				Simulate process compressing
				for (i in 0..10) {
					Thread.sleep(500)
					val percentage = i * 10
					if (percentage == 100) {
						tvStatus.setText(R.string.task_completed)
					} else {
						tvStatus.text = String.format(getString(R.string.compressing), percentage)
					}
				}
			} catch (e: InterruptedException) {
				e.printStackTrace()
			}
		}
	}
}