package com.dean.pokemondemo.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.dean.pokemondemo.PokemonQuery
import com.dean.pokemondemo.R
import com.dean.pokemondemo.databinding.ItemPokemonBinding

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/12
 * Description
 */
class PokemonItemAdapter :
    BaseQuickAdapter<PokemonQuery.Pokemon_v2_pokemon, BaseDataBindingHolder<ItemPokemonBinding>>(R.layout.item_pokemon),
    LoadMoreModule {
    override fun convert(
        holder: BaseDataBindingHolder<ItemPokemonBinding>,
        item: PokemonQuery.Pokemon_v2_pokemon
    ) {
        holder.dataBinding?.run {
            bean = item
            index = holder.adapterPosition
            executePendingBindings()
        }
    }
}