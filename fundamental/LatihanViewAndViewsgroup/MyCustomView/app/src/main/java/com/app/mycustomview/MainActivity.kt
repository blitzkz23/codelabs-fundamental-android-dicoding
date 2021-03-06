package com.app.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

	private lateinit var myButton: MyButton
	private lateinit var myEditText: MyEditText

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		myButton = findViewById(R.id.my_button)
		myEditText = findViewById(R.id.my_edit_text)
		// Melakukan pengecekan saat pertama kali activity terbentuk
		setMyButtonEnable()

		// Menambahkan metode ketika text terjadi perubahan
		myEditText.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				setMyButtonEnable()
			}

			override fun afterTextChanged(s: Editable) {

			}
		})

		// Menambahkan aksi klik kepada button
		myButton.setOnClickListener { Toast.makeText(this@MainActivity, myEditText.text, Toast.LENGTH_SHORT).show() }
	}

	// Metode untuk mengubah disable dan enable pada button
	private fun setMyButtonEnable() {
		val result = myEditText.text
		myButton.isEnabled = result != null && result.toString().isNotEmpty()
	}
}