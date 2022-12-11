package com.pyroblinchik.newsfinder.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T> : Fragment() {


    var state : Bundle? = null
    private var viewBinding: ViewBinding? = null

    var savedInstanceState: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = constructViewBinding()
        viewBinding?.let { init(it,state) }
        return viewBinding?.root
    }

    override fun onPause() {
        super.onPause()
        state = Bundle()
    }

    @Suppress("UNCHECKED_CAST")
    fun getViewBinding(): T = viewBinding as T

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    abstract fun constructViewBinding(): ViewBinding
    abstract fun init(viewBinding: ViewBinding,savedInstanceState: Bundle?)
}