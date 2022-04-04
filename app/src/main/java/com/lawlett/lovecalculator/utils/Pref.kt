package com.lawlett.lovecalculator.utils

import android.content.SharedPreferences
import javax.inject.Inject

class Pref @Inject constructor(private val preferences: SharedPreferences) {
    fun saveStateBoard() {
        preferences.edit().putBoolean("show", true).apply()
    }

    fun isBoardShow(): Boolean {
        return preferences.getBoolean("show", false)
    }

    fun isStartShow(): Boolean {
        return preferences.getBoolean("start", false)
    }

    fun saveStateStart() {
        preferences.edit().putBoolean("start", true).apply()
    }
}