package me.alexnaupay.platziarchitecture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import me.alexnaupay.platziarchitecture.entities.Coupon
import me.alexnaupay.platziarchitecture.viewmodel.CouponsViewModel

class RecyclerCouponsAdapter(
    private val couponsViewModel: CouponsViewModel,
    private val resource: Int
): RecyclerView.Adapter<RecyclerCouponsAdapter.CardCouponHolder>() {

    private var coupons: List<Coupon>? = null

    fun setCouponsList(coupons: List<Coupon>?) {
        this.coupons = coupons
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCouponHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardCouponHolder(binding)
    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int) {
        // coupon at OR default coupon
        val coupon = this.coupons?.get(p1) ?: Coupon(null)
        p0.setCouponDataCard(couponsViewModel, coupon)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        // All of them are car_coupon.xml
        return resource
    }

    class CardCouponHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        private val binding: ViewDataBinding

        init {
            this.binding = binding
        }

        fun setCouponDataCard(couponsViewModel: CouponsViewModel, coupon: Coupon) {
            binding.setVariable(BR.modelv, couponsViewModel)
            binding.setVariable(BR.coupon, coupon)

            binding.executePendingBindings()
        }
    }
}

