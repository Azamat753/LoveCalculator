package com.lawlett.lovecalculator.presenter

import com.lawlett.lovecalculator.base.BasePresenter
import com.lawlett.lovecalculator.data.LoveDao
import com.lawlett.lovecalculator.presenter.view.HistoryView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class HistoryPresenter
@Inject constructor(private val loveDao: LoveDao) : BasePresenter<HistoryView>() {
    suspend fun getAllList() = viewState.showAllList(loveDao.getAllList())
}