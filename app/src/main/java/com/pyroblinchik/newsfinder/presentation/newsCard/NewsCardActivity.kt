package com.pyroblinchik.newsfinder.presentation.newsCard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pyroblinchik.newsfinder.R
import com.pyroblinchik.newsfinder.SKApplication
import com.pyroblinchik.newsfinder.databinding.ActivityNewsCardBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.util.view.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class NewsCardActivity : AppCompatActivity(), ISetToolbar, IProgressView {

    private val viewModel: NewsCardActivityViewModel by viewModels()

    private lateinit var binding: ActivityNewsCardBinding

    private val progressView: ProgressBar by lazy {
        binding.progressView
    }

    private val toolbar by lazy {
        binding.includeToolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsCardBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_NewsFinder)
        setContentView(binding.root)

        initUI()
    }

    override fun setToolbar() {
        // TODO "M" Add toolbar
        setSupportActionBar(toolbar.mainToolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.includeToolbar.mainToolbar.setLogo(R.drawable.ic_logo_horizontal)
    }

//    private fun setToolbarTitle() {
//        binding.includeToolbar.titleToolbar.text =
//            "${getString(R.string.)} ${inspection.?.name ?: ""}"
//    }

    private fun initUI() {
        setToolbar()


        addObservers()
        addClickListeners()
    }

    fun addObservers() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is NewsCardUIState.Error -> {
                            Timber.e("error")
//                            checkStateForTimeout(state.message)
                            hideLoading()
                        }
                        is NewsCardUIState.Loading -> {
                            Timber.e("loading")
                            showLoading()
                        }
                        is NewsCardUIState.Finish -> {
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

    private fun invalidateToolbar() {
//        toolbar.searchContainer.toggleVisibility(viewModel.searchState.value!!)
//        toolbar.titleToolbar.toggleVisibility(!viewModel.searchState.value!!)
    }

    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }

    companion object {
        private const val MODE_UNKNOWN = 0

        var news = News()
        var requestCode = MODE_UNKNOWN


        fun startForResult(
            activity: Activity,
            news: News,
            result: ActivityResultLauncher<Intent>,
        ) {
            this.news = news
            result.launch(
                Intent(activity, NewsCardActivity::class.java),
            )
        }
    }
}