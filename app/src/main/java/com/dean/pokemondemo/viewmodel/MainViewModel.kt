package com.dean.pokemondemo.viewmodel

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo3.api.Optional
import com.dean.pokemondemo.ColorAndSpeciesQuery
import com.dean.pokemondemo.ColorBean
import com.dean.pokemondemo.PokemonQuery
import com.dean.pokemondemo.base.BaseViewModel
import com.dean.pokemondemo.common.GlobalData
import com.dean.pokemondemo.type.Pokemon_v2_pokemon_bool_exp
import com.dean.pokemondemo.type.String_comparison_exp
import com.dean.pokemondemo.util.ApolloClientUtils
import com.dean.pokemondemo.util.ToastUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce


/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/11
 * Description
 */
class MainViewModel : BaseViewModel() {
    val searchStr by lazy { MutableLiveData<String>() }
    private val _query by lazy { MutableStateFlow("") }
    val query = _query
    private var offset = 0
    private var limit = 20
    var isLoadMore = false

    //all color list
    private val colorList by lazy { GlobalData.colorList }

    //all species map
    private val speciesMap by lazy { GlobalData.speciesMap }

    //search result
    val pokemonResultList by lazy { MutableLiveData<MutableList<PokemonQuery.Pokemon_v2_pokemon>>() }

    init {
        launch {
            /**
             * Discard within 300ms
             */
            _query.debounce(300).collect {
                if (it.isNullOrEmpty()) return@collect
                netSearch(it)
            }
        }
    }

    /**
     * get all color and species
     */
    fun getColorAndSpecies() {
        launch {
            val response =
                ApolloClientUtils.getApolloClient().query(ColorAndSpeciesQuery()).execute()
            val data = response.data ?: return@launch ToastUtils.toast("request error")
            data.pokemon_v2_pokemoncolor.forEach {
                when (it.name) {
                    "black" -> colorList.add(ColorBean(true, Color.BLACK))
                    "blue" -> colorList.add(ColorBean(true, Color.BLUE))
                    "brown" -> colorList.add(ColorBean(true, 0xFFF4A460.toInt()))
                    "gray" -> colorList.add(ColorBean(true, Color.GRAY))
                    "green" -> colorList.add(ColorBean(false, Color.GREEN))
                    "pink" -> colorList.add(ColorBean(false, 0xFFFFC0CB.toInt()))
                    "purple" -> colorList.add(ColorBean(false, 0xFF800080.toInt()))
                    "red" -> colorList.add(ColorBean(false, Color.RED))
                    "white" -> colorList.add(ColorBean(false, Color.WHITE))
                    "yellow" -> colorList.add(ColorBean(false, Color.YELLOW))
                }
            }
            data.pokemon_v2_pokemonspecies.forEach {
                speciesMap[it.id] = it.name
            }
            Log.d("===", "getColorAndSpecies: $colorList, $speciesMap")
        }
    }

    /**
     * text changes to search
     * discard within 300ms
     */
    fun toSearch(key: String, offset: Int = 0, limit: Int = 20) {
        isLoadMore = false
        if (key.isNullOrEmpty()) return
        this.offset = offset
        this.limit = limit
        _query.value = key
    }

    /**
     * load more
     */
    fun loadMore(offset: Int, limit: Int = 20) {
        isLoadMore = true
        this.offset = offset
        this.limit = limit
        netSearch(_query.value)
    }

    /**
     * real net search
     * synchronization to prevent inconsistent server returns
     */
    @Synchronized
    private fun netSearch(key: String) {
        Log.d("===", "realSearch: $key, $offset, $limit, $isLoadMore")
        launch {
            val response = ApolloClientUtils.getApolloClient().query(
                PokemonQuery(
                    Optional.present(offset), Optional.present(limit), Optional.present(
                        Pokemon_v2_pokemon_bool_exp(
                            name = Optional.present(
                                String_comparison_exp(_iregex = Optional.present(key))
                            )
                        )
                    )
                )
            ).execute()
            val data = response.data?.pokemon_v2_pokemon
                ?: return@launch ToastUtils.toast("request error")
            pokemonResultList.postValue(data.toMutableList())
        }
    }


}