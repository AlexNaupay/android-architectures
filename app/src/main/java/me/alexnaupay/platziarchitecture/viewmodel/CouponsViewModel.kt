package me.alexnaupay.platziarchitecture.viewmodel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import me.alexnaupay.platziarchitecture.R
import me.alexnaupay.platziarchitecture.RecyclerCouponsAdapter
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.model.CouponObservable

class CouponsViewModel : ViewModel() {

    private val couponObservable = CouponObservable()
    private var recyclerCouponsAdapter: RecyclerCouponsAdapter? = null

    private val selected:MutableLiveData<Coupon> = MutableLiveData<Coupon>()

    fun callCoupons() {
        couponObservable.callCoupons()
    }

    fun getCoupons(): MutableLiveData<List<Coupon>> {
        return couponObservable.getCoupons()
    }


    fun setCouponsInRecyclerAdapter(coupons: List<Coupon>) {
        recyclerCouponsAdapter?.setCouponsList(coupons)
        recyclerCouponsAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerCouponsAdapter(): RecyclerCouponsAdapter? {
        recyclerCouponsAdapter = RecyclerCouponsAdapter(this, R.layout.card_coupon)
        return recyclerCouponsAdapter
    }

    fun getCouponAt(position: Int): Coupon? {
        val coupons: List<Coupon>? = couponObservable.getCoupons().value
        return coupons?.get(position)
    }

    fun getSelectedCoupon(): MutableLiveData<Coupon> {
        return selected
    }
    fun onItemClick(index: Int){
        val coupon = getCouponAt(index)
        selected.value = coupon
    }
}

@BindingAdapter("imageUrl")
fun getImageCouponAt(imgCouponDetail: CircleImageView, imageUrl: String) {
    Picasso.get().load(imageUrl).resize(520, 520).centerCrop().into(imgCouponDetail)
}