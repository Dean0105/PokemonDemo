package com.dean.pokemondemo.util

import android.widget.Toast
import com.dean.pokemondemo.App

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/12
 * Description
 */
object ToastUtils {

    fun toast(content: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(App.mContext, content, duration).show()
    }
}