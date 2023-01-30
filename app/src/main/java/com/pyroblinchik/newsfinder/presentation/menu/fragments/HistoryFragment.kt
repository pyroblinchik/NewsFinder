package com.pyroblinchik.newsfinder.presentation.menu.fragments

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.pyroblinchik.newsfinder.databinding.FragmentHistoryBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.presentation.base.BaseFragment
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivityViewModel
import com.pyroblinchik.newsfinder.presentation.menu.view.NewsAdapter
import com.pyroblinchik.newsfinder.presentation.newsCard.NewsCardActivity
import com.pyroblinchik.newsfinder.util.view.toggleVisibility
import com.pyroblinchik.newsfinder.util.view.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    // TODO "M" code HistoryFragment

    private val viewModel by activityViewModels<MenuActivityViewModel>()
    override fun constructViewBinding(): ViewBinding =
        FragmentHistoryBinding.inflate(layoutInflater)

    private lateinit var newsAdapter: NewsAdapter

    private val emptyResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

    }


    override fun init(viewBinding: ViewBinding, savedInstanceState: Bundle?) {
        initUI()
    }

    fun addObservers() {
        viewModel.updateHistoryNewsList()
        viewModel.newsHistory.observe(this) { list ->
            getViewBinding().listViewHistoryNews.toggleVisibility(list.isNotEmpty())
            newsAdapter.submitList(list)
        }
    }

    fun initUI() {
        setHistoryNews()
        setupClickListeners()
        addObservers()
    }

    private fun setupClickListeners() {

    }

    override fun onResume() {
        viewModel.updateActiveTab(1)
        super.onResume()
    }

    private fun setHistoryNews() {
        val onNewsClickListener: ((News) -> Unit) = {
            NewsCardActivity.startForResult(requireActivity(), it, emptyResult)
        }
        newsAdapter = NewsAdapter(onNewsClickListener)
        getViewBinding().listViewHistoryNews.apply {
            this.adapter = newsAdapter
            this.layoutManager = LinearLayoutManager(requireContext())
            this.visible()
        }
    }
}