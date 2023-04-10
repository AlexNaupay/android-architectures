package me.alexnaupay.platziarchitecture.presenter

import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.interactor.CouponsInteractor
import me.alexnaupay.platziarchitecture.model.interactor.CouponsInteractorImpl
import me.alexnaupay.platziarchitecture.view.CouponView


class CouponPresenterImpl(private val couponView: CouponView): CouponPresenter, CouponsReceiverListener{

    private val couponInteractor: CouponsInteractor = CouponsInteractorImpl()

    override fun getCoupons() {
        this.couponInteractor.getCouponsAPI(this)
    }

    override fun onCouponsReceived(coupons: ArrayList<Coupon>) {
        showCoupons(coupons = coupons)
    }

    override fun showCoupons(coupons: ArrayList<Coupon>) {
        this.couponView.showCoupons(coupons)
    }

}