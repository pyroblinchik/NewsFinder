package com.pyroblinchik.newsfinder.presentation.favourites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pyroblinchik.newsfinder.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
    }
    // TODO "I" create viewModel and UIState
}