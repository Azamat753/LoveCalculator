package com.lawlett.lovecalculator.presenter.view

import com.lawlett.lovecalculator.data.LoveModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface HistoryView : MvpView {
    fun showAllList(list: List<LoveModel>)
}