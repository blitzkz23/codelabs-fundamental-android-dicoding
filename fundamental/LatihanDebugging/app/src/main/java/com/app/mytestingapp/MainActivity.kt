package com.app.mytestingapp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), View.OnClickListener {
	private lateinit var btnSetValue: Button
	private lateinit var tvText: TextView
	private lateinit var imgPreview: ImageView

	private var names = ArrayList<String>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		tvText = findViewById(R.id.tv_text)
//		Tidak inisialisasi pada button dapat menyebabkan null pointer exception
		btnSetValue = findViewById(R.id.btn_set_value)

		btnSetValue.setOnClickListener(this)

		names.add("Narendra")
		names.add("Kenshi")
		names.add("Yonezu")

		imgPreview = findViewById(R.id.img_preview)
//		Ukuran gambar ini terlalu besar sehingga menyebabkan error out of memory, solusi menggunakan glide untuk mengecilkan size
//		imgPreview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fronalpstock_big))
		Glide.with(this).load(R.drawable.fronalpstock_big).into(imgPreview)
//		Library glide dapat Anda periksa pada tautan berikut: https://github.com/bumptech/glide.
//		Glide juga sering digunakan untuk memuat gambar yang berasal dari internet dengan memasukkan tautan gambarnya.
	}

	override fun onClick(v: View) {

		if (v.id == R.id.btn_set_value) {
			Log.d("MainActivity", names.toString())
			val names = StringBuilder()
//			Collection dimulai dari 0, sehingga jika range loopingnya 0-3 maka akan error index out of bonds sehingga diganti 2 sebagai solusinya
			for (i in 0..2) {
				names.append(names[i]).append("\n")
			}
			tvText.text = names.toString()
		}
	}
}