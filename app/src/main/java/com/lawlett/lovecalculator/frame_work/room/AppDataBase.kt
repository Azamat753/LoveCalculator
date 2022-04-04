package com.lawlett.lovecalculator.frame_work.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lawlett.lovecalculator.data.LoveDao
import com.lawlett.lovecalculator.data.LoveModel

@Database(entities = [LoveModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun loveDao(): LoveDao
}