package me.alexnaupay.platziarchitecture.view

import me.alexnaupay.platziarchitecture.entities.Coupon

interface CouponView {

    /**
     * Show coupons on list
     */
    fun showCoupons(coupons: ArrayList<Coupon>?)

    fun showProgressBar()

    fun hideProgressBar()
}