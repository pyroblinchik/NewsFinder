package com.pyroblinchik.newsfinder.presentation.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pyroblinchik.newsfinder.R
import com.pyroblinchik.newsfinder.SKApplication
import com.pyroblinchik.newsfinder.databinding.ActivityMenuBinding
import com.pyroblinchik.newsfinder.presentation.base.ViewModelFactory
import com.pyroblinchik.newsfinder.presentation.menu.view.NewsAdapter
import com.pyroblinchik.newsfinder.util.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MenuActivity : AppCompatActivity(), ISetToolbar {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as SKApplication).component
    }
    private lateinit var viewModel: MenuActivityViewModel

    private lateinit var binding: ActivityMenuBinding

//    private val progressView: ProgressBar by lazy {
//        binding.progressView
//    }
//
    private val toolbar by lazy {
        binding.includeToolbar
    }

    private val navigation by lazy {
        binding.navView
    }

    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        binding = ActivityMenuBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_NewsFinder)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, viewModelFactory)[MenuActivityViewModel::class.java]

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_menu) as NavHostFragment
        val navController = navHostFragment.navController
        navigation.setupWithNavController(navController)

        setToolbar()

        initUI()
    }

    override fun setToolbar() {
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

        addObservers()
        addClickListeners()
    }

    fun addObservers() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is MenuUIState.Error -> {
                            Timber.e("error")
//                            checkStateForTimeout(state.message)
//                            hideLoading()
                        }
                        is MenuUIState.Loading -> {
                            Timber.e("loading")
//                            showLoading()
                        }
                        is MenuUIState.Finish -> {
                            finish()
                        }
                        else -> {
//                            hideLoading()
                        }
                    }
                }
            }
        }
    }

    private fun addClickListeners() {

    }

    override fun onPause() {
        hideSoftKeyboard(this)
        super.onPause()
    }

    override fun onBackPressed() {
        backPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.empty_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

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

//    override fun showLoading() {
//        progressView.visible()
//    }
//
//    override fun hideLoading() {
//        progressView.gone()
//    }

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