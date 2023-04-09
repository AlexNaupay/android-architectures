package me.alexnaupay.platziarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.ApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //VIEW
        val rvCoupons: RecyclerView = findViewById(R.id.rvCoupons) //UI
        rvCoupons.layoutManager = LinearLayoutManager(this)
        val coupons = ArrayList<Coupon>()
        //VIEW


        //CONTROLLER
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
                    if (coupon.image_url.isNotBlank()){
                        coupons.add(coupon)
                    }

                }
                //VIEW
                rvCoupons.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
                //VIEW
            }


        })
        //CONTROLLER
    }
}