package com.pyroblinchik.newsfinder.presentation.newsCard

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
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

        setTitle()
        setDate()
        setCategory()

        setImage()

        setContent()

        setSource()
        setAuthor()

        setFavouritesBookmark()

        addClickListeners()
    }

    private fun setFavouritesBookmark() {
        if (news.isFavorite){
            binding.addToBookmarkImageView.setImageResource(R.drawable.ic_bookmark_dark_green)
        } else{
            binding.addToBookmarkImageView.setImageResource(R.drawable.ic_bookmark_empty)
        }
    }

    private fun setAuthor() {
        binding.authorTextView.text = news.author
    }

    private fun setSource() {
        // TODO "M"  add source image
        binding.sourceTextView.text = news.source
    }

    private fun setContent() {
        binding.contentTextView.text = news.description
    }

    private fun setImage() {
        try {
            Glide.with(this)
                .load(Uri.parse(news.image))
                .into(binding.imageViewContainer)
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setCategory() {
        binding.categoryTextView.text = news.category
    }

    private fun setDate() {
        binding.dateTextView.text = news.published_at.toString()
    }

    private fun setTitle(){
        binding.headerTextView.text = news.title
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
                val intent = Intent()
                setResult(requestCode, intent)
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
                // ActivityResultLauncher<Type>
                Intent(activity, NewsCardActivity::class.java),
            )
        }
    }
}