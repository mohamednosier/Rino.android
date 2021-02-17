package com.rinho.payment.ui.DisolayAccounts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.rinho.payment.data.repository.GeneralListsRepository
/**
 * perform business logic and store ui states for [NewAccountFragment].
 */
class DisplayAccountsViewModel @ViewModelInject constructor(
    private val generalListsRepository: GeneralListsRepository
) : ViewModel() {







     fun displayaccounts() = generalListsRepository.getAllDetailsAccounts()
    fun acceptAccount() = generalListsRepository.getAllDetailsAccounts()
}