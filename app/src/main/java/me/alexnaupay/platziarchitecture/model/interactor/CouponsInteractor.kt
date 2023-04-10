package me.alexnaupay.platziarchitecture.model.interactor

import me.alexnaupay.platziarchitecture.presenter.CouponsReceiverListener

interface CouponsInteractor {
    fun getCouponsAPI(couponsReceiverCallback: CouponsReceiverListener)

}