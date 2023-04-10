package me.alexnaupay.platziarchitecture.presenter

import me.alexnaupay.platziarchitecture.entities.Coupon

interface CouponsReceiverListener {

    /**
     * Execute when coupons are retrieved from repository
     */
    fun onCouponsReceived (coupons: ArrayList<Coupon>)
}