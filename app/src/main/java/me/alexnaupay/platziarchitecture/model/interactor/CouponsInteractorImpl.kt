package me.alexnaupay.platziarchitecture.model.interactor

import me.alexnaupay.platziarchitecture.model.repository.CouponRepository
import me.alexnaupay.platziarchitecture.model.repository.CouponRepositoryImpl
import me.alexnaupay.platziarchitecture.presenter.CouponPresenter
import me.alexnaupay.platziarchitecture.presenter.CouponsReceiverListener

class CouponsInteractorImpl(): CouponsInteractor {

    private var couponRepository: CouponRepositoryImpl = CouponRepositoryImpl()

    override fun getCouponsAPI(couponsReceiverCallback: CouponsReceiverListener) {
        this.couponRepository.getCouponsApi(couponsReceiverCallback)
    }

}