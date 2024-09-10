package com.example.fetchproject.data.network

import com.example.fetchproject.data.model.Item
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiService {
    private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

    private val api: Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    suspend fun fetchItems(): List<Item> = api.getItems()

    interface Api {
        @GET("hiring.json")
        suspend fun getItems(): List<Item>
    }
}
