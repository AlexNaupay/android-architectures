package me.alexnaupay.platziarchitecture.model.repository

import androidx.lifecycle.MutableLiveData
import me.alexnaupay.platziarchitecture.entities.Coupon

interface CouponRepository {
    fun getCoupuns(): MutableLiveData<List<Coupon>>
    fun callCoupunsAPI()

}