package com.dean.pokemondemo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/11
 * Description
 */
open class BaseViewModel : ViewModel() {

    private val uiScope = viewModelScope

    internal fun launch(block: suspend CoroutineScope.() -> Unit) {
        uiScope.launch {
            block()
        }
    }
}