package com.ajidres.movies.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<T>: AppCompatActivity() where T:ViewBinding{


    private lateinit var windowsController: WindowInsetsControllerCompat

    private var _bind: T? = null

    protected val binding: T get() = _bind!!

    abstract fun initBinding(): T

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bind = initBinding()
        setContentView(binding.root)
        initView(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }

    protected  fun fullScreen(){
        windowsController = WindowInsetsControllerCompat(window, window.decorView)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }




}