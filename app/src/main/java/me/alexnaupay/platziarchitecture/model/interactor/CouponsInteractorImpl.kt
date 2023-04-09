package me.alexnaupay.platziarchitecture.model.interactor

import me.alexnaupay.platziarchitecture.model.repository.CouponRepository
import me.alexnaupay.platziarchitecture.model.repository.CouponRepositoryImpl
import me.alexnaupay.platziarchitecture.presenter.CouponPresenter

class CouponsInteractorImpl(var couponPresenter: CouponPresenter): CouponsInteractor {

    private var couponRepository: CouponRepository = CouponRepositoryImpl(couponPresenter)

    override fun getCouponsAPI() {
        couponRepository.getCoupunsAPI()
    }

}