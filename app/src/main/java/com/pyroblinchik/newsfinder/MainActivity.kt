package com.pyroblinchik.newsfinder

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pyroblinchik.newsfinder.databinding.ActivityMainBinding
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivity
import com.pyroblinchik.newsfinder.util.view.hideSoftKeyboard
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val component by lazy {
        (application as SKApplication).component
    }

    val emptyResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()


        setClickListeners()

        //  getHashCode()
    }

    override fun onPause() {
        hideSoftKeyboard(this)
        super.onPause()
    }

    private fun initUI() {

    }

    private fun setClickListeners() {
        binding.button.setOnClickListener {
            MenuActivity.startForResult(
                this,
                emptyResult,
                binding.button
            )
        }
    }

    companion object {
        fun start(context: Context, view: View) {
            val options = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.width, view.height)
            val i = Intent(context, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(i, options.toBundle())
        }
    }
}