package com.pyroblinchik.newsfinder.presentation.languages.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pyroblinchik.newsfinder.databinding.ListItemWithCheckMarkBinding
import com.pyroblinchik.newsfinder.domain.base.model.Language

class LanguageAdapter(
    val languages: List<Language>
) : RecyclerView.Adapter<LanguageHolder>() {

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
        holder.binding.apply {
            headerTextView.text = languages.elementAt(position).name
            engNameTextView.text = languages.elementAt(position).nameEng
        }

    }

    override fun getItemCount(): Int {
        return languages.size
    }
}