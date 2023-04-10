package me.alexnaupay.platziarchitecture.model.repository

import me.alexnaupay.platziarchitecture.presenter.CouponsReceiverListener

interface CouponRepository {
    fun getCouponsApi(couponsReceiverCallback: CouponsReceiverListener)
}