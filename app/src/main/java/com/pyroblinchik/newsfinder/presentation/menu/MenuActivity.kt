package com.pyroblinchik.newsfinder.presentation.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pyroblinchik.newsfinder.R
import com.pyroblinchik.newsfinder.databinding.ActivityMenuBinding
import com.pyroblinchik.newsfinder.util.view.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MenuActivity : AppCompatActivity(), ISetToolbar, IProgressView {

    private val viewModel: MenuActivityViewModel by viewModels()

    private lateinit var binding: ActivityMenuBinding

    private val progressView: ProgressBar by lazy {
        binding.progressView
    }

    private val toolbar by lazy {
        binding.includeToolbar
    }

    private val navigation by lazy {
        binding.navView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_NewsFinder)
        setContentView(binding.root)

        initUI()
    }

    override fun setToolbar() {
        // TODO "M" Add toolbar and search
        setSupportActionBar(toolbar.mainToolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        binding.includeToolbar.titleToolbar.gone()
        binding.includeToolbar.mainToolbar.setLogo(R.drawable.ic_logo_horizontal)
    }

//    private fun setToolbarTitle() {
//        binding.includeToolbar.titleToolbar.text =
//            "${getString(R.string.)} ${inspection.?.name ?: ""}"
//    }

    private fun initUI() {
        setToolbar()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_menu) as NavHostFragment
        val navController = navHostFragment.navController

        navigation.setupWithNavController(navController)

        // TODO "M" Change tabBar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            changeToolbar(destination.displayName)
            viewModel.updateSearchState(false)
        }

        addObservers()
        addClickListeners()
    }

    fun addObservers() {
        viewModel.activeTab.observe(this) {
            invalidateOptionsMenu()
        }

        viewModel.searchState.observe(this) {
            invalidateToolbar()
            invalidateOptionsMenu()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is MenuUIState.Error -> {
                            Timber.e("error")
//                            checkStateForTimeout(state.message)
                            hideLoading()
                        }
                        is MenuUIState.Loading -> {
                            Timber.e("loading")
                            showLoading()
                        }
                        is MenuUIState.Finish -> {
                            finish()
                        }
                        else -> {
                            hideLoading()
                        }
                    }
                }
            }
        }
    }

    private fun addClickListeners() {

    }

    private fun changeToolbar(nameOfBlock: String) {
        Timber.d(nameOfBlock)
        binding.includeToolbar.titleToolbar.text = ""
        when {
            nameOfBlock.contains("feed") -> {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//                binding.includeToolbar.titleToolbar.text = getString(R.string.title_feed)
            }
            nameOfBlock.contains("history") -> {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//                binding.includeToolbar.titleToolbar.text = getString(R.string.title_history)
            }
            nameOfBlock.contains("profile") -> {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//                binding.includeToolbar.titleToolbar.text = getString(R.string.title_profile)
            }
            else -> {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                binding.includeToolbar.titleToolbar.text = ""
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        if (viewModel.searchState.value == true) {
//            menuInflater.inflate(R.menu.empty_menu, menu)
//            return super.onCreateOptionsMenu(menu)
//        }
        when (viewModel.activeTab.value) {
            0 -> {
                menuInflater.inflate(R.menu.menu_filter, menu)
            }
            1 -> {
                menuInflater.inflate(R.menu.empty_menu, menu)
            }
            2 -> {
                menuInflater.inflate(R.menu.empty_menu, menu)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPause() {
        hideSoftKeyboard(this)
        super.onPause()
    }

    override fun onBackPressed() {
        backPressed()
    }
    // where fragments are changing
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun backPressed() {
        finish()
    }

    private fun invalidateToolbar() {
//        toolbar.searchContainer.toggleVisibility(viewModel.searchState.value!!)
        toolbar.titleToolbar.toggleVisibility(!viewModel.searchState.value!!)
    }

    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }

    companion object {
        private const val MODE_UNKNOWN = 0

        var requestCode = MODE_UNKNOWN


        fun startForResult(
            activity: Activity,
            result: ActivityResultLauncher<Intent>,
            view: View,
        ) {
            createActivityForResult(activity, result, view)
        }

        private fun createActivityForResult(
            activity: Activity,
            result: ActivityResultLauncher<Intent>,
            view: View,
        ) {
            result.launch(
                Intent(activity, MenuActivity::class.java),
            )
        }

    }
}