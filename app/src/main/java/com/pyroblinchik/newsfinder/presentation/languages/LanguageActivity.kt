package com.pyroblinchik.newsfinder.presentation.languages

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pyroblinchik.newsfinder.R
import com.pyroblinchik.newsfinder.databinding.ActivityLanguageBinding
import com.pyroblinchik.newsfinder.databinding.ToolbarBinding
import com.pyroblinchik.newsfinder.domain.base.model.Language
import com.pyroblinchik.newsfinder.presentation.languages.views.LanguageAdapter
import com.pyroblinchik.newsfinder.util.view.IProgressView
import com.pyroblinchik.newsfinder.util.view.ISetToolbar
import com.pyroblinchik.newsfinder.util.view.gone
import com.pyroblinchik.newsfinder.util.view.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LanguageActivity : AppCompatActivity(), ISetToolbar, IProgressView {

    private lateinit var binding: ActivityLanguageBinding

    private val languageViewModel: LanguageViewModel by viewModels()

    private val toolbar: ToolbarBinding by lazy {
        binding.includeToolbar
    }

    private val progressView: ProgressBar by lazy {
        binding.progressView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        // handle toolbar
        setToolbar()

        // handle state behaviour
        addStateBehaviour()

        // handle language list
        setLanguages()
    }

    private fun addStateBehaviour() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // harvest latest state
                languageViewModel.uiState.collectLatest {state: LanguageUIState ->
                    when(state) {
                        is LanguageUIState.Loading -> {
                            showLoading()
                            Timber.d("loading")
                        }
                        is LanguageUIState.Loaded -> {
                            hideLoading()
                            Timber.d("loaded")
                        }
                        is LanguageUIState.Finish -> {
                            hideLoading()
                            finish()
                        }
                        is LanguageUIState.Error -> {
                            Timber.e("error")
                            hideLoading()
                        }
                        else -> {
                            hideLoading()
                        }
                    }
                }
            }
        }
    }

    override fun setToolbar() {
        setSupportActionBar(toolbar.mainToolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.includeToolbar.titleToolbar.setText(R.string.languages)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    // FIXME |I| language list is disappearing if you open and close it several times
    private fun setLanguages() {

        var adapter = LanguageAdapter(listOf())
        lifecycleScope.launch {
            languageViewModel.languages.collectLatest {
                adapter = LanguageAdapter(it)
            }
        }
        binding.languagesListView.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
            it.visible()
        }
    }

    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }

    companion object {
        fun startForResult(
            activity: Activity,
            result: ActivityResultLauncher<Intent>
        ) {
            result.launch(
                Intent(activity, LanguageActivity::class.java)
            )
        }
    }
}