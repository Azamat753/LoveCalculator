package com.lawlett.lovecalculator.activity

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.lawlett.lovecalculator.R
import com.lawlett.lovecalculator.databinding.ActivityMainBinding
import com.lawlett.lovecalculator.utils.Pref
import com.lawlett.lovecalculator.visible
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var pref: Pref
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root) }
        initNavigation()
        initListenerNav()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }


    private fun initListenerNav() {
        if (!pref.isStartShow()) navController.navigate(R.id.startFragment)
        else if (!pref.isBoardShow()) navController.navigate(R.id.boardFragment)
    }
}