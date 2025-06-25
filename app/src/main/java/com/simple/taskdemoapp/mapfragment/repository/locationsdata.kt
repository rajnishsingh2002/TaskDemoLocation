package com.simple.taskdemoapp.mapfragment.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://685b657489952852c2d96229.mockapi.io/mydata/"

    val api: StatusApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StatusApi::class.java)
    }
}
