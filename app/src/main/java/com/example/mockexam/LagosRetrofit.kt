package com.example.mockexam

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_U = "https://api.github.com/"

private val moshis = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshis)).baseUrl(
    BASE_U).build()
interface LagosRetrofits {
    @GET("search/users?q=lagos")
    suspend fun getDatas(): LagosData
}

object LagosApiss {
    val lagosApiServicess: LagosRetrofits by lazy { retrofit.create(LagosRetrofits::class.java) }
}