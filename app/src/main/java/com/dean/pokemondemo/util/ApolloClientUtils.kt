package com.dean.pokemondemo.util

import com.apollographql.apollo3.ApolloClient

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/12
 * Description
 */
object ApolloClientUtils {

    fun getApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl("https://beta.pokeapi.co/graphql/v1beta").build()
    }
}