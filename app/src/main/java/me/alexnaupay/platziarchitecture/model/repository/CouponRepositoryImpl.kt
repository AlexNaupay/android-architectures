package me.alexnaupay.platziarchitecture.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.ApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl: CouponRepository {

    private var coupons = MutableLiveData<List<Coupon>>()
    //Subject MutableLiveData
    //Observers List Coupon
    //Change List Coupon - MutableLiveData
    //observe

    override fun getCoupuns(): MutableLiveData<List<Coupon>> {
        return coupons
    }

    //TODA LA LÓGICA DE CONEXIÓN
    override fun callCoupunsAPI() {
        //CONTROLLER
        val couponsList: ArrayList<Coupon> = ArrayList()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message.toString())
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    val jsonObject = jsonElement.asJsonObject
                    val coupon = Coupon(jsonObject)

                    if (coupon.image_url.isNotBlank()){  // Add only valid urls
                        couponsList.add(coupon)
                    }
                }

                //VIEW
                coupons.value = couponsList
            }
        })
    }
}