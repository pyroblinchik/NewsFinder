package com.pyroblinchik.newsfinder.presentation.menu.view

import androidx.recyclerview.widget.DiffUtil
import com.pyroblinchik.newsfinder.domain.base.model.News

class NewsDiffCallback
    () : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {

        return oldItem == newItem
    }

}
