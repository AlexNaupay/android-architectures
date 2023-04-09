package me.alexnaupay.platziarchitecture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import me.alexnaupay.platziarchitecture.R
import me.alexnaupay.platziarchitecture.RecyclerCouponsAdapter
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.ApiAdapter
import me.alexnaupay.platziarchitecture.presenter.CouponPresenter
import me.alexnaupay.platziarchitecture.presenter.CouponPresenterImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CouponView {
    private var couponPresenter: CouponPresenter? = null
    private var rvCoupons: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        couponPresenter = CouponPresenterImpl(this)

        //VIEW
        rvCoupons = findViewById(R.id.rvCoupons) //UI
        rvCoupons?.layoutManager = LinearLayoutManager(this)

        getCoupons()

    }

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        try {
            rvCoupons!!.adapter = RecyclerCouponsAdapter(coupons!!, R.layout.card_coupon)
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    override fun getCoupons() {
        couponPresenter?.getCoupons()
    }
}