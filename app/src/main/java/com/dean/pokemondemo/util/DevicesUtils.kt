package com.dean.pokemondemo.util

import com.dean.pokemondemo.App

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/11
 * Description
 */
object DevicesUtils {

    //app versionName
    fun getVersionName(): String {
        var result = ""
        val context = App.mContext
        try {
            result = context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }
}