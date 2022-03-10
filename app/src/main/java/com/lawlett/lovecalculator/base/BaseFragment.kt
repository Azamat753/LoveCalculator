package com.lawlett.lovecalculator.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment

abstract class BaseFragment(@LayoutRes layout: Int) : MvpAppCompatFragment(layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initClickers()

}