package me.alexnaupay.platziarchitecture.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
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

        call.enqueue(object : Callback<JsonElement> {

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.i("ERROR: ", "There was an error calling the endpoint")
                Log.e("ERROR: ", t.message.toString())
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                val responseBody = response.body()

                if (responseBody != null) {
                    if (responseBody.isJsonObject) {   // Its Json response
                        val jsonObject = responseBody.asJsonObject
                        if (jsonObject.has("offers") && jsonObject.get("offers").isJsonArray) {
                            val offersJsonArray = jsonObject.getAsJsonArray("offers")
                            offersJsonArray?.forEach { jsonElement: JsonElement ->
                                val coupon = Coupon(jsonElement.asJsonObject)

                                if (coupon.image_url.isNotBlank()){  // Add only valid urls
                                    couponsList.add(coupon)
                                }
                            }
                            //VIEW
                            coupons.value = couponsList.subList(7,15)

                        } else {
                            Log.e("ERROR: ", "No 'offers' array found in response")
                        }
                    } else if (responseBody.isJsonPrimitive) {  // Response body is just a string
                        // Manejar el caso cuando la respuesta es un String
                        val errorString = responseBody.asString
                        Log.e("ERROR: ", errorString)
                        // Show a Toast?
                    }
                }
            }
        })
    }
}