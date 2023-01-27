package com.pyroblinchik.newsfinder.presentation.favourites.views

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pyroblinchik.newsfinder.databinding.ActivityFavouritesBinding
import com.pyroblinchik.newsfinder.databinding.ListItemNewsInFeedBinding
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.presentation.languages.views.LanguageHolder
import com.pyroblinchik.newsfinder.util.view.toggleVisibility
import timber.log.Timber

class FavouritesAdapter(val onFavouritesClickListener: ((News) -> Unit)) :
    ListAdapter<News, FavouritesHolder>(FavouritesDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemNewsInFeedBinding.inflate(
            /* inflater = */ layoutInflater,
            /* parent = */ parent,
            /* attachToParent = */ false
        )
        return FavouritesHolder(binding)
    }

    // bind favourites list to recycler list (view holder)
    override fun onBindViewHolder(holder: FavouritesHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            headerTextView.text = item.title
            bookmarkImageView.toggleVisibility(item.isFavorite)
            setPhoto(root.context, imageViewContainer, Uri.parse(item.image))
            contentTextView.text = item.description
            dateTextView.text = item.historyDate
            categoryTextView.text = item.category

            root.setOnClickListener {
                onFavouritesClickListener.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    private fun setPhoto(context: Context, imageView: ImageView, uri: Uri?) {
        try {
            Glide.with(context)
                .load(uri)
                .into(imageView)
        } catch (e: Exception) {
            Timber.e("Couldn't set photo (from FavouritesAdapter) ;((")
        }
    }
}