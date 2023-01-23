package com.pyroblinchik.newsfinder.presentation.menu.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.pyroblinchik.newsfinder.databinding.FragmentProfileBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.presentation.base.BaseFragment
import com.pyroblinchik.newsfinder.presentation.languages.LanguageActivity
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivityViewModel
import com.pyroblinchik.newsfinder.presentation.newsCard.NewsCardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    // TODO "I" code ProfileFragment (settings)

    val languageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            {

            }

        }

    private val viewModel by activityViewModels<MenuActivityViewModel>()
    override fun constructViewBinding(): ViewBinding =
        FragmentProfileBinding.inflate(layoutInflater)


    override fun init(viewBinding: ViewBinding, savedInstanceState: Bundle?) {
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
        getViewBinding().apply {
            lightRadioButton.setOnClickListener {
                Toast.makeText(context, "Light theme is enabled", Toast.LENGTH_SHORT).show()
            }
            darkRadioButton.setOnClickListener {
                Toast.makeText(context, "Dark theme is enabled", Toast.LENGTH_SHORT).show()
            }
            systemRadioButton.setOnClickListener {
                Toast.makeText(context, "System theme is enabled", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setLanguage() {
        getViewBinding().languageSettingsLL.setOnClickListener {
            LanguageActivity.startForResult(requireActivity(), languageResult)
        }
    }

    private fun setFavourites() {

    }

    override fun onResume() {
        viewModel.updateActiveTab(2)
        super.onResume()
    }
}