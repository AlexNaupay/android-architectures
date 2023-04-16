package me.alexnaupay.platziarchitecture.model

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("getOffers/")
    fun getCoupons(): Call<JsonElement>
}