package com.lawlett.lovecalculator.presenter

import com.lawlett.lovecalculator.data.LoveDao
import com.lawlett.lovecalculator.presenter.view.LoveView
import com.lawlett.lovecalculator.base.BasePresenter
import com.lawlett.lovecalculator.data.LoveCalculatorApi
import com.lawlett.lovecalculator.data.LoveModel
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LovePresenter @Inject constructor(
    private val api: LoveCalculatorApi,
    private val loveDao: LoveDao
) :
    BasePresenter<LoveView>() {


    suspend fun getPercentage(fName: String, mName: String) {
        viewState.showLoading()
        val response = api.getPercentage(femaleName = fName, maleName = mName)
        if (response.isSuccessful) {
            response.body()?.let {
                viewState.closeLoading()
                viewState.showResult(it)
                loveDao.createModel(LoveModel(0, it.femaleName, it.maleName, it.percentage))
            }
        }
    }
}