package com.dean.pokemondemo.util


import android.graphics.Color
import com.dean.pokemondemo.PokemonQuery
import com.dean.pokemondemo.bean.ColorBean
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

fun getAbilities(bean: PokemonQuery.Pokemon_v2_pokemon): String {
    val result = StringBuilder()
    val lastIndex = bean.pokemon_v2_pokemonabilities.size - 1
    bean.pokemon_v2_pokemonabilities.forEachIndexed { index, ability ->
        result.append(ability.pokemon_v2_ability?.name)
        if (lastIndex != index) {
            result.append(", ")
        }
    }
    return result.toString()
}