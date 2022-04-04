package com.lawlett.lovecalculator.presenter.view

import com.lawlett.lovecalculator.data.LoveCalculatorModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
    interface LoveView : MvpView {
        fun showResult(loveModel: LoveCalculatorModel)
        fun showLoading()
        fun closeLoading()
}