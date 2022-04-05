package com.lawlett.lovecalculator.fragment

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lawlett.lovecalculator.R
import com.lawlett.lovecalculator.base.BaseFragment
import com.lawlett.lovecalculator.databinding.FragmentResultBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : BaseFragment(R.layout.fragment_result) {

    private val binding: FragmentResultBinding by viewBinding()
    private val args: ResultFragmentArgs by navArgs()


    private fun setupUI() {
        val present = args.model.percentage + "%"
        with(binding) {
            txtFirst.text = args.model.femaleName
            txtSecond.text = args.model.maleName
            txtResult.text = present
            txtScore.text = args.model.result
        }
    }

    override fun initClickers() {
        initPopBackStack()
        setupUI()
        initBtn()
    }

    private fun initPopBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_resultFragment_to_calculatorFragment)
            }

        })
    }


    private fun initBtn() {
        binding.btnTryAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_calculatorFragment)
        }
        binding.openHistory.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_historyFragment)
        }
    }
}