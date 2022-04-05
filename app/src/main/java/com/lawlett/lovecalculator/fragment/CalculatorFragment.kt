package com.lawlett.lovecalculator.fragment

import android.text.Editable
import android.text.TextWatcher
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
        initEditListener()
    }

    private fun initEditListener() {
        binding.editFirst.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editFirst.text.isNotBlank())
                    binding.femaleEd.isErrorEnabled = false
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.editSecond.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editSecond.text.isNotBlank())
                    binding.maleEd.isErrorEnabled = false
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
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
            val female = binding.editFirst.text.toString()
            val male = binding.editSecond.text.toString()
            checkData(female, male)
        }
    }

    private fun checkData(female: String, male: String) {
        if (female.isBlank() && male.isBlank()) {
            binding.femaleEd.error = "Пусто"
            binding.maleEd.error = "Пусто"
        } else if (male.isBlank()) {
            binding.maleEd.error = "Пусто"
            binding.femaleEd.isErrorEnabled = false
        } else if (female.isBlank()) {
            binding.femaleEd.error = "Пусто"
            binding.maleEd.isErrorEnabled = false
        } else {
            binding.femaleEd.isErrorEnabled = false
            binding.maleEd.isErrorEnabled = false
            lifecycleScope.launch {
                presenter.getPercentage(female, male)
            }
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