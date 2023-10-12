package com.dean.pokemondemo.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
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

    /**
     * app versionName
     */
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

    /**
     * hide soft keyboard
     */
    fun hideSoftInput(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
    }
}