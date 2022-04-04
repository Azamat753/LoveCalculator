package com.lawlett.lovecalculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoveDao {
    @Insert
    suspend fun createModel(loveModel: LoveModel)

    @Query("SELECT * FROM LoveModel")
    suspend fun getAllList(): List<LoveModel>
}