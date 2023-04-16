package me.alexnaupay.platziarchitecture.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.repository.CouponRepository
import me.alexnaupay.platziarchitecture.model.repository.CouponRepositoryImpl

class CouponObservable: BaseObservable() {

    private val couponRepository: CouponRepository = CouponRepositoryImpl()

    //Repositorio
    fun callCoupons(){
        couponRepository.callCoupunsAPI()
    }

    //ViewModel
    fun getCoupons() : MutableLiveData<List<Coupon>> {
        return couponRepository.getCoupuns()
    }
}