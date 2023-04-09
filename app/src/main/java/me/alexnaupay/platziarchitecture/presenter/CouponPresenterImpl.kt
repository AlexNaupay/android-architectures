package me.alexnaupay.platziarchitecture.presenter

import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.interactor.CouponsInteractor
import me.alexnaupay.platziarchitecture.model.interactor.CouponsInteractorImpl
import me.alexnaupay.platziarchitecture.view.CouponView


class CouponPresenterImpl(var couponView: CouponView): CouponPresenter{

    private var couponInteractor: CouponsInteractor = CouponsInteractorImpl(this)

    override fun showCoupons(coupons: ArrayList<Coupon>) {
        couponView.showCoupons(coupons)
    }

    override fun getCoupons() {
        couponInteractor.getCouponsAPI()
    }

}