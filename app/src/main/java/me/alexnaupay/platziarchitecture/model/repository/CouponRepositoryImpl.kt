package me.alexnaupay.platziarchitecture.model.repository

import android.util.Log
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.ApiAdapter
import me.alexnaupay.platziarchitecture.presenter.CouponPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl(var couponPresenter: CouponPresenter): CouponRepository {


    //TODA LA LÓGICA DE CONEXIÓN
    override fun getCoupunsAPI() {
        //CONTROLLER
        var coupons: ArrayList<Coupon>? = ArrayList<Coupon>()
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
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    coupons?.add(coupon)
                }
                //VIEW
                if (coupons != null) {
                    couponPresenter.showCoupons(coupons)
                }
            }




        })
        //CONTROLLER




        //couponPresenter.showCoupons()
    }

}