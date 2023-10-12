package com.dean.pokemondemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/11
 * Description
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResource())
        initVM()
        binding.lifecycleOwner = this
        initView()
        initData()
    }

    /**
     * set xml
     */
    abstract fun getLayoutResource(): Int

    /**
     * init viewModel
     */
    abstract fun initVM()

    /**
     * init view
     */
    abstract fun initView()

    /**
     * init data
     */
    abstract fun initData()


}