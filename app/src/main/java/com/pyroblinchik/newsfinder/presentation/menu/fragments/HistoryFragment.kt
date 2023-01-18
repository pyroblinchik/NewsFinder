package com.pyroblinchik.newsfinder.presentation.menu.fragments

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.pyroblinchik.newsfinder.databinding.FragmentHistoryBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.presentation.base.BaseFragment
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivityViewModel
import com.pyroblinchik.newsfinder.util.view.toggleVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    // TODO "M" code HistoryFragment

    private val viewModel by activityViewModels<MenuActivityViewModel>()
    override fun constructViewBinding(): ViewBinding =
        FragmentHistoryBinding.inflate(layoutInflater)

//    lateinit var historyNewsAdapter: HistoryNewsAdapter

    override fun init(viewBinding: ViewBinding,savedInstanceState: Bundle?) {
        initUI()
    }

    fun addObservers() {
        viewModel.newsHistory.observe(this) { list ->
            getViewBinding().listViewHistoryNews.toggleVisibility(list.isNotEmpty())
//            checkEmptyState(list)
//            newsAdapter.submitList(list)
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
        val onItemClickListener: ((news: News) -> Unit) = {

        }

//        historyNewsAdapter = HistoryNewsAdapter(
//            onItemClickListener
//        )
//        getViewBinding().listViewHistoryNews.adapter = historyNewsAdapter
    }
}