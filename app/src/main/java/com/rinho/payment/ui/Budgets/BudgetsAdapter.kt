package com.rinho.payment.ui.transportation.Budgets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.rinho.payment.AppExecutors
import com.rinho.payment.R
import com.rinho.payment.databinding.ItemTransportsBinding
import com.rinho.payment.models.Budgets.BudgetsResponse
import com.rinho.payment.ui.base.DataBoundListAdapter

/**
 * Adapter that displays list of transportation.
 *
 * @property dataBindingComponent DataBindingComponent The DataBindingComponent to use in the binding.
 * @param appExecutors AppExecutors executor pools.
 * @property onCompanyClicked Function1<TransportationItem, Unit> action to perform when hotel selected.
 */
class BudgetsAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val onCompanyClicked: (BudgetsResponse) -> Unit
) : DataBoundListAdapter<BudgetsResponse, ItemTransportsBinding>(
    appExecutors,
    object : DiffUtil.ItemCallback<BudgetsResponse>() {
        override fun areItemsTheSame(
            oldItem: BudgetsResponse,
            newItem: BudgetsResponse
        ) = oldItem== newItem

        override fun areContentsTheSame(
            oldItem: BudgetsResponse,
            newItem: BudgetsResponse
        ) = oldItem == newItem
    }) {

    override fun createBinding(parent: ViewGroup): ItemTransportsBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_transports,
        parent,
        false,
        dataBindingComponent
    )

    override fun bind(
        binding: ItemTransportsBinding,
        item: BudgetsResponse,
        position: Int
    ) {
        binding.transport = item

//        binding.containerHeader.setOnClickListener {
//            onCompanyClicked(item)
//        }
    }
}