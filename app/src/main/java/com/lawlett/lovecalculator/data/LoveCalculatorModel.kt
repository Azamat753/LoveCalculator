package com.lawlett.lovecalculator.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoveCalculatorModel(
    @SerializedName("fname")
    val femaleName: String,
    @SerializedName("sname")
    val maleName: String,
    @SerializedName("percentage")
    val percentage: String,
    @SerializedName("result")
    val result: String
) : Parcelable