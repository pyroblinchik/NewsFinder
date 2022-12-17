package com.pyroblinchik.newsfinder.presentation.menu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.pyroblinchik.newsfinder.databinding.FragmentHomeBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.presentation.base.BaseFragment
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivityViewModel
import com.pyroblinchik.newsfinder.presentation.menu.view.NewsAdapter
import com.pyroblinchik.newsfinder.util.view.toggleVisibility

class FeedFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by activityViewModels<MenuActivityViewModel>()
    override fun constructViewBinding(): ViewBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    lateinit var newsAdapter: NewsAdapter

    override fun init(viewBinding: ViewBinding,savedInstanceState: Bundle?) {
        initUI()
    }

    fun addObservers() {
        viewModel.news.observe(this) { list ->
            getViewBinding().listViewNews.toggleVisibility(list.isNotEmpty())
//            checkEmptyState(list)
            newsAdapter.submitList(list)
        }
    }

    fun initUI() {
        setNews()
        setupClickListeners()
        addObservers()
    }

    private fun setupClickListeners() {

    }

    private fun setNews() {
        val onItemClickListener: ((news: News) -> Unit) = {

        }

        newsAdapter = NewsAdapter(
            onItemClickListener
        )
        getViewBinding().listViewNews.adapter = newsAdapter
    }
}