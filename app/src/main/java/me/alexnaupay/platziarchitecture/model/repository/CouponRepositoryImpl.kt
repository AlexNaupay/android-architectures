package me.alexnaupay.platziarchitecture.model.repository

import android.util.Log
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.ApiAdapter
import me.alexnaupay.platziarchitecture.presenter.CouponsReceiverListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl() : CouponRepository {

    override fun getCouponsApi(couponsReceiverCallback: CouponsReceiverListener) {
        val coupons: ArrayList<Coupon> = ArrayList()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message.toString())

                // coupons will be empty, if an error occurs
                couponsReceiverCallback.onCouponsReceived(coupons)

                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    val jsonObject = jsonElement.asJsonObject
                    val coupon = Coupon(jsonObject)

                    if (coupon.image_url.isNotBlank()){  // Add only valid urls
                        coupons.add(coupon)
                    }
                }

                // Call listener
                couponsReceiverCallback.onCouponsReceived(coupons)
            }
        })
    }

}