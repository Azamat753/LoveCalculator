package com.lawlett.lovecalculator.activity

import android.os.Bundle
import com.lawlett.lovecalculator.R
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}