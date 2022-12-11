package com.pyroblinchik.newsfinder.presentation.menu.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pyroblinchik.newsfinder.databinding.ListItemNewsLiteBinding
import com.pyroblinchik.newsfinder.domain.base.model.News

class NewsAdapter(
    private val onItemClickListener: ((news: News) -> Unit)
) : ListAdapter<News, NewsHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = ListItemNewsLiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                text.text = description
            }
        }
    }
}
