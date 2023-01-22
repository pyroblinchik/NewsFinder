package com.pyroblinchik.newsfinder.presentation.languages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pyroblinchik.newsfinder.R
import com.pyroblinchik.newsfinder.databinding.ActivityLanguageBinding
import com.pyroblinchik.newsfinder.domain.base.model.Language
import com.pyroblinchik.newsfinder.util.view.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLanguages()
    }

    private fun setLanguages() {
        val languages = listOf(
            Language(
                id = 0,
                name = "English",
                nameEng = "English",
                code = "??"
            ),
            Language(
                id = 1,
                name = "Русский",
                nameEng = "Russian",
                code = "??"
            ),
            Language(
                id = 2,
                name = "Deutsch",
                nameEng = "German",
                code = "??"
            ),
        )
        val adapter = LanguageAdapter(languages)
        binding.languagesListView.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
            it.visible()
        }
    }
}