package com.lawlett.lovecalculator.di

import com.lawlett.lovecalculator.data.LoveCalculatorApi
import com.lawlett.lovecalculator.LovePresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoveApi(): LoveCalculatorApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://love-calculator.p.rapidapi.com/").build()
            .create(LoveCalculatorApi::class.java)
    }
    @Provides
    fun provideLovePresenter(): LovePresenter {
        return LovePresenter(provideLoveApi())
    }
}