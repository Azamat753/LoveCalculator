package com.lawlett.lovecalculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoveModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val female: String,
    val male: String,
    val present: String
)
