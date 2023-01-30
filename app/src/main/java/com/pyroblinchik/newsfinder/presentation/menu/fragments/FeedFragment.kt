package com.pyroblinchik.newsfinder.presentation.menu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.pyroblinchik.newsfinder.R
import com.pyroblinchik.newsfinder.databinding.FragmentFeedBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.presentation.base.BaseFragment
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivityViewModel
import com.pyroblinchik.newsfinder.presentation.menu.view.NewsAdapter
import com.pyroblinchik.newsfinder.presentation.newsCard.NewsCardActivity
import com.pyroblinchik.newsfinder.util.view.toggleVisibility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    private val viewModel by activityViewModels<MenuActivityViewModel>()
    override fun constructViewBinding(): ViewBinding =
        FragmentFeedBinding.inflate(layoutInflater)

    lateinit var newsAdapter: NewsAdapter

    val emptyResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        }

    override fun init(viewBinding: ViewBinding,savedInstanceState: Bundle?) {
        initUI()
    }

    fun addObservers() {
        viewModel.news.observe(this) { list ->
            getViewBinding().newsListView.toggleVisibility(list.isNotEmpty())
            checkEmptyState(list)
            newsAdapter.submitList(list)
        }
    }

    fun initUI() {
        setNews()
        setupClickListeners()
        addObservers()
    }

    private fun setupClickListeners() {
        // TODO "M" remove getViewBinding
    }


    private fun checkEmptyState(list: ArrayList<News>?) {
        getViewBinding().emptyStateFeed.headerTextView.text =
            this.getString(R.string.nothing_found)
        getViewBinding().emptyStateFeed.contentTextView.text =
            this.getString(R.string.changing_query_message)
        getViewBinding().emptyStateFeed.root.toggleVisibility(list.isNullOrEmpty())
    }

    override fun onResume() {
        viewModel.updateActiveTab(0)
        super.onResume()
    }

    private fun setNews() {
        val onItemClickListener: ((news: News) -> Unit) = {
            viewModel.addToHistory(news = it)
            NewsCardActivity.startForResult(requireActivity(),it,emptyResult)
        }

        newsAdapter = NewsAdapter(
            onItemClickListener
        )
        getViewBinding().newsListView.adapter = newsAdapter
    }
}