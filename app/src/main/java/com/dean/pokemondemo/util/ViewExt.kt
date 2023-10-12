package com.dean.pokemondemo.util

import android.graphics.Color
import com.dean.pokemondemo.ColorBean
import com.dean.pokemondemo.common.GlobalData

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/12
 * Description
 */
fun getSpeciesName(id: Int): String {
    return GlobalData.speciesMap.getOrDefault(id, "unknown")
}

fun getBackgroundColor(index: Int): ColorBean {
    return GlobalData.colorList.getOrNull(index % 10) ?: ColorBean(false, Color.WHITE)
}