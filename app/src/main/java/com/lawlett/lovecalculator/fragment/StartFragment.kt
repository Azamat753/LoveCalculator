package com.lawlett.lovecalculator.fragment

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.lawlett.lovecalculator.R
import com.lawlett.lovecalculator.base.BaseFragment
import com.lawlett.lovecalculator.databinding.FragmentStartBinding
import com.lawlett.lovecalculator.utils.Pref
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : BaseFragment(R.layout.fragment_start) {
    private val binding: FragmentStartBinding by viewBinding()

    @Inject
    lateinit var pref: Pref
    override fun initClickers() {
        initBtn()
        initPopBackStack()
    }

    private fun initPopBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = requireActivity().finish()
            })
    }

    private fun initBtn() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_boardFragment)
            pref.saveStateStart()
        }
    }

}