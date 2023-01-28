package com.pyroblinchik.newsfinder.presentation.favourites

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pyroblinchik.newsfinder.R
import com.pyroblinchik.newsfinder.databinding.ActivityFavouritesBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.presentation.favourites.views.FavouritesAdapter
import com.pyroblinchik.newsfinder.util.view.IProgressView
import com.pyroblinchik.newsfinder.util.view.ISetToolbar
import com.pyroblinchik.newsfinder.util.view.gone
import com.pyroblinchik.newsfinder.util.view.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class FavouritesActivity : AppCompatActivity(), ISetToolbar, IProgressView {

    private lateinit var binding: ActivityFavouritesBinding

    private lateinit var  favouritesAdapter: FavouritesAdapter

    private val favouritesViewModel: FavouritesViewModel by viewModels()

//    private val toolbar: ToolbarBinding by lazy {
//        binding.includeToolbar
//    }

    private val progressView: ProgressBar by lazy {
        binding.progressView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        // handle toolbar
        setToolbar()

        // handle favourites list
        setFavourites()

        // handle state behaviour
        addObserver()
    }

    private fun addObserver() {
        lifecycleScope.launch {

            val submitList = async {
                favouritesViewModel.favourites.collectLatest {
                    favouritesAdapter.submitList(it)
                }
            }

            val addStateBehaviour = async {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    favouritesViewModel.state.collectLatest { state: FavouritesUIState ->
                        when(state) {
                            is FavouritesUIState.Loading -> {
                                Timber.d("loading")
                                showLoading()
                            }
                            is FavouritesUIState.Loaded -> {
                                Timber.d("loaded")
                                hideLoading()
                            }
                            is FavouritesUIState.Finish -> {
                                Timber.d("finished")
                                hideLoading()
                                finish()
                            }
                            is FavouritesUIState.Error -> {
                                Timber.d("error")
                                hideLoading()
                            }
                            else -> {
                                hideLoading()
                            }
                        }
                    }
                }
            }
            submitList.await()
            addStateBehaviour.await()
        }
    }

    override fun setToolbar() {
//        setSupportActionBar(toolbar.mainToolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.includeToolbar.titleToolbar.setText(R.string.favourites)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavourites() {
        val onFavouritesClickListener: ((News) -> Unit) = {
            Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
        }
        favouritesAdapter = FavouritesAdapter(onFavouritesClickListener)
        binding.favouritesListView.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@FavouritesActivity)
            this.visible()
        }
    }



    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }


    // responsible for launching this activity for result
    companion object {
        fun startForResult(
            activity: Activity,
            result: ActivityResultLauncher<Intent>
        ) {
            result.launch(
                Intent(activity, FavouritesActivity::class.java)
            )
        }
    }
}