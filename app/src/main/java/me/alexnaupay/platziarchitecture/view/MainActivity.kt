package me.alexnaupay.platziarchitecture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.alexnaupay.platziarchitecture.R
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.viewmodel.CouponsViewModel

class MainActivity : AppCompatActivity(){


    private var couponViewModel: CouponsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //VIEW
        setupBindings(savedInstanceState)


    }

    fun setupBindings(savedInstanceState: Bundle?){
        var activityMainBinding: me.alexnaupay.platziarchitecture.databinding.ActivityMainBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_main)

        couponViewModel = ViewModelProviders.of(this).get(CouponsViewModel::class.java)

        activityMainBinding.setModel(couponViewModel)
        setupListUpdate()

    }

    fun setupListUpdate(){
        //CallCoupons
        couponViewModel?.callCoupons()
        //getCoupons - Lista de cupones
        couponViewModel?.getCoupons()?.observe(this, Observer {
                coupons: List<Coupon> ->
            Log.w("COUPON", coupons.get(0).title)
            couponViewModel?.setCouponsInRecyclerAdapter(coupons)
        })
    }

}
