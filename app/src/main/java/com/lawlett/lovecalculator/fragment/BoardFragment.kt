package com.lawlett.lovecalculator.fragment

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.lawlett.lovecalculator.*
import com.lawlett.lovecalculator.adapter.ViewPagerAdapter
import com.lawlett.lovecalculator.base.BaseFragment
import com.lawlett.lovecalculator.data.BoardModel
import com.lawlett.lovecalculator.databinding.FragmentBoardBinding
import com.lawlett.lovecalculator.utils.Pref
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BoardFragment : BaseFragment(R.layout.fragment_board) {
    private val binding: FragmentBoardBinding by viewBinding()
    private val adapter = ViewPagerAdapter()

    @Inject
    lateinit var pref: Pref


    private fun initPopBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = requireActivity().finish()
            })
    }


    override fun initClickers() {
        initPopBackStack()
        initAdapter()
        initPagerListener()
        initBtn()
    }

    private fun initBtn() {
        binding.btnTry.setOnClickListener {
            findNavController().navigate(R.id.action_boardFragment_to_calculatorFragment)
            pref.saveStateBoard()
        }
    }

    private fun initPagerListener() {
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == getBoardList().size - 1) binding.btnTry.visible()
                else binding.btnTry.gone()
            }
        })
    }

    private fun initAdapter() {
        binding.pager.adapter = adapter
        adapter.setData(getBoardList())
        binding.dotsIndicator.setViewPager2(binding.pager)
    }

    private fun getBoardList(): ArrayList<BoardModel> {
        val list: ArrayList<BoardModel> = arrayListOf()
        list.add(
            BoardModel(
                R.drawable.board2,
                R.drawable.back2,
                "Have a good time",
                "You should take the time to help those who need you."
            )
        )
        list.add(
            BoardModel(
                R.drawable.board3,
                R.drawable.back3,
                "Cherishing love",
                "It is now no longer possible for you to cherish love."
            )
        )
        list.add(
            BoardModel(
                R.drawable.board4,
                R.drawable.back4,
                "Have a breakup?",
                "we have made the corecction for you \n" +
                        "dont worry :)\n" +
                        "maybe someone is waiting for you!"
            )
        )
        return list
    }


}