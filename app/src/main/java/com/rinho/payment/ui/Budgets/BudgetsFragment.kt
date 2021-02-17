package com.rinho.payment.ui.transportation.Budgets

import android.util.Log
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rinho.payment.R
import com.rinho.payment.databinding.FragmentTransportsSearchBinding
import com.rinho.payment.ui.base.BaseFragment

import com.rinho.payment.util.EndlessRecyclerViewScrollListener
import com.rinho.payment.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

/**
 * Display UI of transportation search form.
 */
@AndroidEntryPoint
class BudgetsFragment :
    BaseFragment<BudgetsViewModel, FragmentTransportsSearchBinding>(
        BudgetsViewModel::class.java
    ) {
    private var transportsAdapter by autoCleared<BudgetsAdapter>()

    /**
     * A recyclerView scrollListener to handle paging.
     */
    private var scrollListener by autoCleared<EndlessRecyclerViewScrollListener>()
    override fun getLayoutRes() = R.layout.fragment_transports_search

    override fun init() {
//        viewModel.allbudgets.observe(viewLifecycleOwner, ::handleApiStatus)



        initBudgetsList()
    }



// return budget list

    private fun initBudgetsList() {



        transportsAdapter = BudgetsAdapter(dataBindingComponent, appExecutors) {

        }

        binding.recyclerHotels.adapter = transportsAdapter

        scrollListener = object :
            EndlessRecyclerViewScrollListener(binding.recyclerHotels.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {

            }
        }

        binding.recyclerHotels.removeOnScrollListener(scrollListener)
        binding.recyclerHotels.addOnScrollListener(scrollListener)

        viewModel.budgetList.observe(viewLifecycleOwner) {
            Log.d("fetchContacts", "fetchContacts1: "+it.toString())
//            it.sortByDescending { it.balance }
            transportsAdapter.submitList(it)
            transportsAdapter.notifyDataSetChanged()
        }
//                Log.d("accounts", "accounts: "+it.data.toString())
//            }
//        }


    }

}