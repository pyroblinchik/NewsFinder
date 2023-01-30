package com.pyroblinchik.newsfinder.presentation.languages.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pyroblinchik.newsfinder.databinding.ListItemWithCheckMarkBinding
import com.pyroblinchik.newsfinder.domain.base.model.Language

class LanguageAdapter(
    val onLanguageClickListener: ((Language) -> Unit)
    // enables us to submit list
) : ListAdapter<Language, LanguageHolder>(LanguageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemWithCheckMarkBinding.inflate(
            /* inflater = */ layoutInflater,
            /* parent = */ parent,
            /* attachToParent = */ false
        )
        return LanguageHolder(binding)
    }

    // attach language list to view holder
    override fun onBindViewHolder(holder: LanguageHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            headerTextView.text = item.name
            engNameTextView.text = item.nameEng

            root.setOnClickListener {
                onLanguageClickListener.invoke(item)
            }
        }

    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}