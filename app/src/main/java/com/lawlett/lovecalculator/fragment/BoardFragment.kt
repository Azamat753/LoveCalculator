package com.lawlett.lovecalculator.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.lawlett.lovecalculator.PagerListener
import com.lawlett.lovecalculator.R
import com.lawlett.lovecalculator.ViewPagerAdapter
import com.lawlett.lovecalculator.base.BaseFragment
import com.lawlett.lovecalculator.data.AppPreferences
import com.lawlett.lovecalculator.data.BoardModel
import com.lawlett.lovecalculator.databinding.FragmentBoardBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import java.util.concurrent.Flow
import java.util.prefs.Preferences


class BoardFragment : BaseFragment(R.layout.fragment_board), PagerListener {
    private val binding: FragmentBoardBinding by viewBinding()
    private val adapter = ViewPagerAdapter(this)
    val Context.dataStore by preferencesDataStore(name = "settings")
    val IS_SHOW_BOARD = booleanPreferencesKey("is_board_show")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

    }

    val isShowPreferencesFlow =
        requireContext().dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val isShow = preferences[IS_SHOW_BOARD] ?: false
            AppPreferences(isShow)
        }

    override fun onStartClick() {
        val isShowFlow = requireContext().dataStore.data.map { preferences ->
            preferences[IS_SHOW_BOARD] ?: false
        }
    }

    suspend fun changeValue(isShow: Boolean) {
        requireContext().dataStore.edit { preferences ->
            preferences[IS_SHOW_BOARD] = isShow
        }
    }

    override fun initClickers() {

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
                R.drawable.board1,
                R.drawable.back1,
                "love calculator",
                "Find out the compatibility of your names.\n"
            )
        )
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