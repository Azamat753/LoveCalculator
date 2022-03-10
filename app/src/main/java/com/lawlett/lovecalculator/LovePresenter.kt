package com.lawlett.lovecalculator

import com.lawlett.lovecalculator.base.BasePresenter
import com.lawlett.lovecalculator.data.LoveCalculatorApi
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LovePresenter @Inject constructor(val api: LoveCalculatorApi) :
    BasePresenter<LoveView>() {


    fun getPercentage(fName: String, mName: String) {
        viewState.showLoading()
        launch {
            val response = api.getPercentage(femaleName = fName, maleName = mName)
            if (response.isSuccessful) {
                response.body()?.let {
                    viewState.closeLoading()
                    viewState.showResult(it)
                }
            }
        }
    }
}