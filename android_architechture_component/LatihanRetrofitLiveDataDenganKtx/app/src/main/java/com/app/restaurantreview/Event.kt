package com.app.restaurantreview

open class Event<out T>(private val content: T) {
	/**
	 * Kelas ini untuk menandai ketika event sudah dihandled sehingga saat perubahan orientasi, event tidak dipanggil lagi
	 */

	@Suppress("MemberVisibilityCanBePrivate")
	var hasBeenHandled = false
		private set


	fun getContentIfNotHandled(): T? {
		//Jika event sudah dihandle sebelumnya, maka tidak usah return apa2.
		return if (hasBeenHandled) {
			null
		} else {
			hasBeenHandled = true
			content
		}
	}

	fun peekContent(): T = content
}