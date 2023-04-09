package me.alexnaupay.platziarchitecture.presenter

import me.alexnaupay.platziarchitecture.entities.Coupon

interface CouponPresenter {
    //Vista
    fun showCoupons(coupons: ArrayList<Coupon>)

    //Interactor
    fun getCoupons()
}