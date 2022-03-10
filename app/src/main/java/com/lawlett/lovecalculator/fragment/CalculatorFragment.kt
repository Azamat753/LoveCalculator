package com.lawlett.lovecalculator.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.lawlett.lovecalculator.data.LoveCalculatorModel
import com.lawlett.lovecalculator.LovePresenter
import com.lawlett.lovecalculator.LoveView
import com.lawlett.lovecalculator.R
import com.lawlett.lovecalculator.base.BaseFragment
import com.lawlett.lovecalculator.databinding.FragmentCalculatorBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class CalculatorFragment : BaseFragment(R.layout.fragment_calculator), LoveView {

    private val binding: FragmentCalculatorBinding by viewBinding()

    @InjectPresenter
    lateinit var presenter: LovePresenter

    @Inject
    lateinit var hiltPresenter: LovePresenter

    @ProvidePresenter
    fun providePresenter(): LovePresenter {
        return hiltPresenter
    }

    private var female: String = ""
    private var male: String = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
    }

    override fun initClickers() {
        binding.calculateBtn.setOnClickListener {
            female = binding.femaleEd.text.toString()
            male = binding.maleEd.text.toString()
            presenter.getPercentage(female, male)
        }
    }

    override fun showResult(loveModel: LoveCalculatorModel) {
        val action =
            CalculatorFragmentDirections.actionCalculatorFragmentToResultFragment(loveModel)
        findNavController().navigate(action)
    }

    override fun showLoading() {
        binding.progressBar.isVisible = true
    }

    override fun closeLoading() {
        binding.progressBar.isGone = true
    }
}