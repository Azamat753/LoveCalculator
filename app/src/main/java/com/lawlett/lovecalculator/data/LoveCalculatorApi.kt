package com.lawlett.lovecalculator.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveCalculatorApi {
    @GET("getPercentage")
    suspend fun getPercentage(
        @Query("sname") femaleName: String,
        @Query("fname") maleName: String,
        @Header("X-RapidAPI-Host")
        host: String = "love-calculator.p.rapidapi.comm",
        @Header("X-RapidAPI-Key")
        key: String = "30ff2e3f45mshdf615a789b9759cp14f62fjsnfd251b64f853"
    ): Response<LoveCalculatorModel>
}