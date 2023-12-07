package com.example.api.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
}).build()

val apollo = ApolloClient.Builder()
    .serverUrl("https://rickandmortyapi.com/graphql")
    .okHttpClient(okHttpClient)
    .build()