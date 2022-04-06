package com.lawlett.lovecalculator.fragment

import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.lawlett.lovecalculator.adapter.HistoryAdapter
import com.lawlett.lovecalculator.R
import com.lawlett.lovecalculator.base.BaseFragment
import com.lawlett.lovecalculator.data.LoveCalculatorModel
import com.lawlett.lovecalculator.data.LoveModel
import com.lawlett.lovecalculator.databinding.FragmentHistoryBinding
import com.lawlett.lovecalculator.presenter.HistoryPresenter
import com.lawlett.lovecalculator.presenter.view.HistoryView
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*
import java.util.Collections.reverse
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : BaseFragment(R.layout.fragment_history), HistoryView {
    private val binding: FragmentHistoryBinding by viewBinding()
    private val adapter = HistoryAdapter()

    @InjectPresenter
    lateinit var presenter: HistoryPresenter

    @Inject
    lateinit var hiltPresenter: HistoryPresenter

    @ProvidePresenter
    fun providePresenter(): HistoryPresenter {
        return hiltPresenter
    }

    override fun initClickers() {
        setupUi()
        initRoom()
        initPopBackStack()
    }

    private fun initPopBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_historyFragment_to_calculatorFragment)
            }

        })
    }

    private fun initRoom() {
        lifecycleScope.launch {
            presenter.getAllList()
        }
    }

    private fun setupUi() {
        binding.rvHistory.adapter = adapter
    }


    override fun showAllList(list: List<LoveModel>) {
        reverse(list)
        adapter.setData(list)
    }

}