package com.pyroblinchik.newsfinder.presentation.menu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pyroblinchik.newsfinder.databinding.FragmentProfileBinding
import com.pyroblinchik.newsfinder.presentation.base.BaseFragment
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivityViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    // TODO "I" code ProfileFragment (settings)

    private val viewModel by activityViewModels<MenuActivityViewModel>()
    override fun constructViewBinding(): ViewBinding =
        FragmentProfileBinding.inflate(layoutInflater)


    override fun init(viewBinding: ViewBinding,savedInstanceState: Bundle?) {
        initUI()
    }

    fun initUI() {
        setTheme()
        setLanguage()
        setFavourites()
        setupClickListeners()
        addObservers()
    }

    fun addObservers() {

    }

    private fun setupClickListeners() {

    }

    private fun setTheme() {
        getViewBinding().favouritesTextView
    }

    private fun setLanguage() {

    }

    private fun setFavourites() {

    }

    override fun onResume() {
        viewModel.updateActiveTab(2)
        super.onResume()
    }
}