package com.dean.pokemondemo.common

import com.dean.pokemondemo.ColorBean

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/12
 * Description
 */
object GlobalData {
    //all color list
    val colorList by lazy { mutableListOf<ColorBean>() }

    //all species map
    val speciesMap by lazy { HashMap<Int, String>() }

    fun clean() {
        colorList.clear()
        speciesMap.clear()
    }
}