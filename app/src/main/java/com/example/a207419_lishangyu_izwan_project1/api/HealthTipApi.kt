package com.example.a207419_lishangyu_izwan_project1.api

import retrofit2.http.GET

interface HealthTipApi {
    @GET("advice")
    suspend fun getAdvice(): AdviceResponse
}