package com.rinho.payment.ui.Accounts.New_Account

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rinho.payment.data.repository.GeneralListsRepository
import com.rinho.payment.models.InsertAccounts
import com.rinho.payment.util.Event

/**
 * perform business logic and store ui states for [NewAccountFragment].
 */
class NewAccountViewModel @ViewModelInject constructor(
    private val generalListsRepository: GeneralListsRepository
) : ViewModel() {

    /**
     * type, default is [Male]
     */
    private val _type = MutableLiveData(Type.Checking)

    /**
     * Immutable version of [_travellerGender].
     */
    val type_list = _type as LiveData<Type>

    val accountname = MutableLiveData("")

    val accountbalance = MutableLiveData("")

    fun setSelectedType(value: Int?) {
        _type.value = Type.values().find { it.value == value } ?: Type.Checking
    }
    private val _navigate = MutableLiveData<Event<Unit>>()

    /**
     * Immutable version of [_navigateToMainActivity]
     */
    val navigate = _navigate as LiveData<Event<Unit>>
    /**
     * Make api request to add the account  and return the response.
     */
    fun insertAccount(account: InsertAccounts) =
        generalListsRepository.inserAccount(account.apply {

            if(_type.value?.value==1){
                this.accounts.type="checking"
            }else if(_type.value?.value==2){
                this.accounts.type="savings"
            }else{
                this.accounts.type="creditCard"
            }

        })
    fun createInsertAccount() =InsertAccounts(
        accounts=Accounts_insert()
    )
    fun Accounts_insert()=InsertAccounts.Accounts1(
        id="",
        name =accountname.value!!,
        type = "checking",
        on_budget=false,
        closed=false,
        note = "",
        balance = Integer.parseInt(accountbalance.value!!),
        cleared_balance = 0,
        uncleared_balance = 0,
    transfer_payee_id="",
        deleted = false



    )
    /**
     * the id of order to get its details
     */
    private val orderId = MutableLiveData<String>()
    fun updateDetailsRequest(
        id: String
    ) {
        if (orderId.value != id)
            orderId.value = id
        Log.d("initHotelsAndTra", "initHotelsAndTransportation9: ")
    }
    val details = orderId.switchMap { generalListsRepository.getAllDetails(it) }
fun accept_statues()= orderId.switchMap {generalListsRepository.getAllApproveandDenyStatues(it,"approve")}.apply {
//        observeForever {
////            Log.d("fetchContacts", "fetchContacts2: " + it.data?.data)
//            if (it.data != null) {
//                _navigate.postValue(Event(Unit))
//            }
//        }
    }

    fun deny_statues() =
            orderId.switchMap { generalListsRepository.getAllApproveandDenyStatues(it, "deny") }
                .apply {
//                    observeForever {
//
//                        if (it.data != null) {
//                            _navigate.postValue(Event(Unit))
//                        }
//                    }
//                }
    }
}