package com.dean.pokemondemo

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/11
 * Description
 */
class App : Application(), ViewModelStoreOwner {
    companion object {
        lateinit var mContext: App

        //shared viewModel
        val sharedViewModelStore by lazy { ViewModelStore() }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    override fun getViewModelStore(): ViewModelStore {
        return sharedViewModelStore
    }
}