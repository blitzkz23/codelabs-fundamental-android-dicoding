package com.app.restaurantreview.api

import com.app.restaurantreview.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class ApiConfig {
	companion object {
		fun getApiService(): ApiService {
//			val loggingInterceptor =
//				HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//			val client = OkHttpClient.Builder()
//				.addInterceptor(loggingInterceptor)
//				.build()

			val httpClient = OkHttpClient.Builder()

			if (BuildConfig.DEBUG) {
				val loggingInterceptor = HttpLoggingInterceptor()
				loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
				httpClient.addInterceptor(loggingInterceptor)
			}

			val retrofit = Retrofit.Builder()
				.baseUrl("https://restaurant-api.dicoding.dev/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(httpClient.build())
				.build()
			return retrofit.create(ApiService::class.java)
		}
	}
}