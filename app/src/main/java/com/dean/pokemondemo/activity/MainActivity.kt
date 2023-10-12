package com.dean.pokemondemo.activity

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.pokemondemo.App
import com.dean.pokemondemo.R
import com.dean.pokemondemo.adapter.PokemonItemAdapter
import com.dean.pokemondemo.base.BaseActivity
import com.dean.pokemondemo.common.GlobalData
import com.dean.pokemondemo.databinding.ActivityMainBinding
import com.dean.pokemondemo.util.DevicesUtils
import com.dean.pokemondemo.viewmodel.MainViewModel

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/11
 * Description
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { PokemonItemAdapter() }

    override fun getLayoutResource() = R.layout.activity_main

    override fun initVM() {
        mainViewModel = ViewModelProvider(App.mContext).get(MainViewModel::class.java)
    }

    override fun initView() {
        binding.vm = mainViewModel
        binding.rvList.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
            setHasFixedSize(true)
        }
        //no data show
        mAdapter.setEmptyView(R.layout.view_empty)
        //load more
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            mainViewModel.loadMore(mAdapter.data.size)
        }
        //click
        mAdapter.setOnItemClickListener { adapter, view, position ->
            DevicesUtils.hideSoftInput(this)
            mainViewModel.pokemonBean.value = mAdapter.getItem(position)
            startActivity(Intent(this, DetailActivity::class.java))

        }
    }

    override fun initData() {
        mainViewModel.getColorAndSpecies()
        mainViewModel.searchStr.observe(this) {
            Log.d("===", it)
            if (it.trim().isNullOrEmpty()) mAdapter.setNewInstance(null)
            mainViewModel.toSearch(it.trim())
        }
        mainViewModel.pokemonResultList.observe(this) {
            Log.d("===", "search result: $it")
            if (mainViewModel.isLoadMore) {
                if (it.isNullOrEmpty()) {
                    mAdapter.loadMoreModule.loadMoreEnd()
                } else {
                    mAdapter.addData(it)
                    mAdapter.loadMoreModule.loadMoreComplete()
                }
            } else {
                mAdapter.setNewInstance(it)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalData.clean()
    }


}