package com.lawlett.lovecalculator.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoveCalculatorModel(
    @SerializedName("sname")
    val femaleName: String,
    @SerializedName("fname")
    val maleName: String,
    val percentage: Int,
    val result: String
) : Parcelable