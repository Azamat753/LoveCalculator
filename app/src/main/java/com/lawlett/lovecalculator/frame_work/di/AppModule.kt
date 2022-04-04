package com.lawlett.lovecalculator.frame_work.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.lawlett.lovecalculator.frame_work.room.AppDataBase
import com.lawlett.lovecalculator.data.LoveDao
import com.lawlett.lovecalculator.data.LoveCalculatorApi
import com.lawlett.lovecalculator.presenter.HistoryPresenter
import com.lawlett.lovecalculator.presenter.LovePresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideLovePresenter(loveDao: LoveDao): LovePresenter {
        return LovePresenter(provideLoveApi(), loveDao)
    }

    @Singleton
    @Provides
    fun shared(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Pref", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun appDataBase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "Aboba").build()

    @Provides
    fun loveDao(appDataBase: AppDataBase): LoveDao = appDataBase.loveDao()

    @Provides
    fun provideHistoryPresenter(loveDao: LoveDao): HistoryPresenter =
        HistoryPresenter(loveDao)
}