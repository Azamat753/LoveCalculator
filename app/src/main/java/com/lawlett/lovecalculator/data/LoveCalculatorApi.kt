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
        @Header("x-rapidapi-host")
        host: String = "love-calculator.p.rapidapi.com",
        @Header("x-rapidapi-key")
        key: String = "78bb8ecc8dmsha71f7db1db6c66cp1338f5jsn2ae3a25d70d2"
    ): Response<LoveCalculatorModel>
}