package com.rinho.payment.ui.transportation.Budgets

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rinho.payment.data.repository.GeneralListsRepository
import com.rinho.payment.models.Budgets

/**
 * perform business logic and store ui states for [BudgetsFragment].
 */
class BudgetsViewModel @ViewModelInject constructor(
    private val generalListsRepository: GeneralListsRepository
) : ViewModel() {




    /**
     * The list of hotels in the current page.
     */
    private val _budgetsList = MutableLiveData<MutableList<Budgets.BudgetsResponse>>(ArrayList())

    /**
     * Immutable version of [_BudgetsList].
     */
    val budgetList = _budgetsList as LiveData<MutableList<Budgets.BudgetsResponse>>

// resturn list of budgets
//    val allbudgets=generalListsRepository.getAllBudgets().apply {
//        observeForever {
//            Log.d("fetchContacts", "fetchContacts2: "+it.data?.budgets)
//            if (it.data != null) {
//                Log.d("fetchContacts", "fetchContacts: "+it.data.budgets)
//                _budgetsList.value =
//                    _budgetsList.value!!.apply { addAll(it.data.budgets) }
//            }
//        }
//    }


   }