package com.lawlett.lovecalculator.fragment

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.lawlett.lovecalculator.data.LoveCalculatorModel
import com.lawlett.lovecalculator.presenter.LovePresenter
import com.lawlett.lovecalculator.presenter.view.LoveView
import com.lawlett.lovecalculator.R
import com.lawlett.lovecalculator.base.BaseFragment
import com.lawlett.lovecalculator.databinding.FragmentCalculatorBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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


    override fun initClickers() {
        initPopBackStack()
        initBtn()
    }

    private fun initPopBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        })
    }

    private fun initBtn() {
        binding.calculateBtn.setOnClickListener {
            val female = binding.femaleEd.text.toString()
            val male = binding.maleEd.text.toString()
            checkData(female, male)
        }
    }

    private fun checkData(female: String, male: String) {
        if (female.isBlank() && male.isBlank()) {
            binding.femaleEd.error = "Пусто"
            binding.maleEd.error = "Пусто"
            binding.femaleEd.requestFocus()
            binding.maleEd.requestFocus()
        } else if (male.isBlank()) {
            binding.maleEd.error = "Пусто"
            binding.maleEd.requestFocus()
        } else if (female.isBlank()) {
            binding.femaleEd.requestFocus()
            binding.femaleEd.error = "Пусто"
        } else {
            lifecycleScope.launch {
                presenter.getPercentage(female, male)
            }
        }

    }


    override fun showResult(loveModel: LoveCalculatorModel) {
        Log.e("ABOBA", "lOL")
        val action =
            CalculatorFragmentDirections.actionCalculatorFragmentToResultFragment(loveModel)
        findNavController().navigate(action)
    }

    override fun showLoading() {
        Log.e("ABOBA", "Show")
        binding.progressBar.isVisible = true
    }

    override fun closeLoading() {
        Log.e("ABOBA", "Close")
        binding.progressBar.isGone = true
    }
}