package com.pyroblinchik.newsfinder.presentation.languages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pyroblinchik.newsfinder.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
    }
}