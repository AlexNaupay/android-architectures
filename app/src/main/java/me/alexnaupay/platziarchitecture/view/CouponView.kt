package me.alexnaupay.platziarchitecture.view

import me.alexnaupay.platziarchitecture.entities.Coupon

interface CouponView {

    //Vista
    fun showCoupons(coupons: ArrayList<Coupon>?)

    //Presentador
    fun getCoupons()
}