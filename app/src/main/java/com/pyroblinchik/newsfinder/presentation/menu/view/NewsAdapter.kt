package com.pyroblinchik.newsfinder.presentation.menu.view

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.pyroblinchik.newsfinder.R
import com.pyroblinchik.newsfinder.databinding.ListItemNewsInFeedBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.util.DateStringFormatter
import com.pyroblinchik.newsfinder.util.view.margin
import com.pyroblinchik.newsfinder.util.view.toggleVisibility
import com.pyroblinchik.newsfinder.util.view.visible

class NewsAdapter(
    private val onItemClickListener: ((news: News) -> Unit)
) : ListAdapter<News, NewsHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = ListItemNewsInFeedBinding.inflate(
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
                headerTextView.text = title

                bookmarkImageView.toggleVisibility(isFavorite)

                contentTextView.text = description

                imageViewContainer.visible()
                // TODO "M" Change imageView setter
                setPhoto(root.context, imageViewContainer, Uri.parse(image))

                // TODO "M" Change date setter
                dateTextView.text = "September 1st, 2022"
//                    DateStringFormatter().parseStringToDateWithMonthConventional(published_at).toString()

                categoryTextView.text = category
            }
        }
    }

    private fun setPhoto(context: Context, imageView: ImageView, uri: Uri) {
        try {
            Glide.with(context)
                .load(uri)
                .into(imageView)
        } catch (e: Exception) {
        }

    }
}
