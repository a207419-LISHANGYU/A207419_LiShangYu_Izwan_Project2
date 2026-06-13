package com.example.a207419_lishangyu_izwan_project1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: HealthTipApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.adviceslip.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HealthTipApi::class.java)
    }
}