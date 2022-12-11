package com.pyroblinchik.newsfinder.util.view

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.pyroblinchik.newsfinder.R

class ToolbarSetup(
    private val type: String,
    private val toolbar: Toolbar
) {

    fun getToolbar() : Toolbar {
        val title = toolbar.findViewById<TextView>(R.id.title_toolbar)
        when(type){
            usualToolbar-> {
                title.text = ""
            }
        }
        return toolbar
    }


    companion object {
        const val usualToolbar = "USUAL_TOOLBAR"
    }
}