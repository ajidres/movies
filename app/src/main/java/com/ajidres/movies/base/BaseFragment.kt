package com.ajidres.movies.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ajidres.movies.extentions.clearFlagProgress
import com.ajidres.movies.extentions.gone
import com.ajidres.movies.extentions.setFlagProgress
import com.ajidres.movies.extentions.visible

abstract class BaseFragment<T : ViewBinding>: Fragment() {

    private var _bind: T? = null
    protected val binding: T get() = _bind!!

    lateinit var progressBar:View

    abstract fun initBinding(): T

    abstract fun initView(view: View, savedInstanceState: Bundle?)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = initBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }

    fun showProgress(value:Boolean){
        if (value) {
            requireActivity().window.setFlagProgress()
            progressBar.visible()
        } else {
            requireActivity().window.clearFlagProgress()
            progressBar.gone()
        }
    }


}