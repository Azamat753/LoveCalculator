package com.lawlett.lovecalculator.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lawlett.lovecalculator.R
import com.lawlett.lovecalculator.base.BaseFragment
import com.lawlett.lovecalculator.databinding.FragmentResultBinding
import com.redmadrobot.extensions.viewbinding.viewBinding

class ResultFragment : BaseFragment(R.layout.fragment_result) {

    private val binding: FragmentResultBinding by viewBinding()
    private val args: ResultFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            maleTv.text = args.loveModel.maleName
            femaleTv.text = args.loveModel.femaleName
            scoreTv.text = args.loveModel.percentage.toString()
            resultTv.text = args.loveModel.result
        }
    }

    override fun initClickers() {
        binding.tryAgain.setOnClickListener {
            findNavController().navigate(R.id.calculatorFragment)
        }
    }
}