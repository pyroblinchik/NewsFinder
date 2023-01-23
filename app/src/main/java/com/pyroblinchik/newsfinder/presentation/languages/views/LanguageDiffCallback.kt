package com.pyroblinchik.newsfinder.presentation.languages.views

import androidx.recyclerview.widget.DiffUtil
import com.pyroblinchik.newsfinder.domain.base.model.Language

// utility that calculates the difference between two lists
class LanguageDiffCallback: DiffUtil.ItemCallback<Language>() {
    override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
        return oldItem == newItem
    }
}