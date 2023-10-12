package com.dean.pokemondemo.activity

import androidx.lifecycle.ViewModelProvider
import com.dean.pokemondemo.App
import com.dean.pokemondemo.R
import com.dean.pokemondemo.base.BaseActivity
import com.dean.pokemondemo.databinding.ActivityDetailBinding
import com.dean.pokemondemo.viewmodel.MainViewModel

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/12
 * Description
 */
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    private lateinit var mainViewModel: MainViewModel

    override fun getLayoutResource() = R.layout.activity_detail

    override fun initVM() {
        mainViewModel = ViewModelProvider(App.mContext).get(MainViewModel::class.java)
    }

    override fun initView() {
        binding.toolbar.let {
            setSupportActionBar(it)
            it.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            it.setNavigationOnClickListener {
                finish()
            }
        }
        binding.vm = mainViewModel
    }

    override fun initData() {
        mainViewModel.pokemonBean.postValue(mainViewModel.pokemonBean.value)
    }
}