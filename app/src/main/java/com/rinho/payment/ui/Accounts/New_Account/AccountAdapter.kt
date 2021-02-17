package com.rinho.payment.ui.Accounts.New_Account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.rinho.payment.AppExecutors
import com.rinho.payment.R
import com.rinho.payment.databinding.ItemHotelBinding
import com.rinho.payment.models.Accounts1

import com.rinho.payment.ui.base.DataBoundListAdapter

/**
 * Adapter that displays list of hotels.
 *
 * @property dataBindingComponent DataBindingComponent The DataBindingComponent to use in the binding.
 * @param appExecutors AppExecutors executor pools.
 * @property onHotelClicked Function1<Hotel, Unit> action to perform when hotel selected.
 */
class AccountAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val appExecutors: AppExecutors,
    private val onHotelClicked: (Accounts1) -> Unit
) : DataBoundListAdapter<Accounts1, ItemHotelBinding>(
    appExecutors,
    object : DiffUtil.ItemCallback<Accounts1>() {
        override fun areItemsTheSame(oldItem: Accounts1, newItem: Accounts1) = false
        override fun areContentsTheSame(oldItem: Accounts1, newItem: Accounts1) = false
    }) {

    override fun createBinding(parent: ViewGroup): ItemHotelBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_hotel,
        parent,
        false,
        dataBindingComponent
    )

    override fun bind(binding: ItemHotelBinding, item: Accounts1, position: Int) {
        binding.hotel = item

//        binding.recyclerAmenities.adapter =
//            HotelAmenitiesAdapter(dataBindingComponent, appExecutors).apply {
//                submitList(item.amenities)
//            }

        binding.root.setOnClickListener { onHotelClicked(item) }
    }
}