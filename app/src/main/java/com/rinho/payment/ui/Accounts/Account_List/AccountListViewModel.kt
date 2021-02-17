package com.rinho.payment.ui.Accounts.Account_List

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rinho.payment.data.repository.GeneralListsRepository
import com.rinho.payment.models.Accounts1
import com.rinho.payment.models.Filter

/**
 * perform business logic and store ui states for [AccountListFragment].
 */
class AccountListViewModel @ViewModelInject constructor(
    private val generalListsRepository: GeneralListsRepository
) : ViewModel() {


    /**
     * The list of Accounts in the current page.
     */
    private val _accountsList = MutableLiveData<MutableList<Accounts1>?>(ArrayList())

    /**
     * Immutable version of [_accountList].
     */
    val accountsList = _accountsList as LiveData<MutableList<Accounts1>>


    //result of total of list
    private val _navigatewithresult = MutableLiveData<String>()

    /**
     * Immutable version of [_navigateToMainActivity]
     */
    val navigatewithresult = _navigatewithresult as LiveData<String>


    /**
     * The search request that has all search prams.
     */
    private val _filter = MutableLiveData<Filter>()

    /**
     * Immutable version of [_hotelSearchRequest].
     */
    val filter = _filter as LiveData<Filter>


//Get All Accounts
    val allcontacts=generalListsRepository.getAllAccounts().apply {
        observeForever {
            Log.d("fetchContacts", "fetchContacts2: "+it.data?.data)
            Log.d("fetchContacts", "fetchContacts5: "+it.data?.total)
            if (it.data != null) {
            Log.d("fetchContacts", "fetchContacts: "+it.data.data)

_navigatewithresult.postValue(""+it.data?.total)
                _accountsList.value =
                    _accountsList.value!!.apply { addAll(it.data.data) }
        }
        }
    }


    /**
     * if [hotelsResponse] has more pages fetch the next one.
     */
    fun fetchNextPage(x:String) {
//        _accountsList.value?.run {
//            if (pageIndex * 20 < allcontacts.value?.data?.totalCount ?: 0)
//                _hotelSearchRequest.value = this.apply { pageIndex++ }
//        }

        //Get All Accounts
         generalListsRepository.getAllAccounts1(x).apply {
            observeForever {
                Log.d("fetchContacts", "fetchContacts2: " + it.data?.data)
                if (it.data != null) {
                    Log.d("fetchContacts", "fetchContacts: " + it.data.data)
                    _accountsList.value =
                        _accountsList.value!!.apply { clear() }
                    _accountsList.value =
                        _accountsList.value!!.apply { addAll(it.data.data) }
                }
            }
        }

    }
    fun search(x:String) {
//        _accountsList.value?.run {
//            if (pageIndex * 20 < allcontacts.value?.data?.totalCount ?: 0)
//                _hotelSearchRequest.value = this.apply { pageIndex++ }
//        }

        //Get All Accounts
        generalListsRepository.search(x).apply {
            observeForever {
                Log.d("fetchContacts", "fetchContacts2: " + it.data?.data)
                if (it.data != null) {
                    Log.d("fetchContacts", "fetchContacts: " + it.data.data)
                    _accountsList.value =
                        _accountsList.value!!.apply { clear() }
                    _accountsList.value =
                        _accountsList.value!!.apply { addAll(it.data.data) }
                }
            }
        }

    }

    fun filter(x:String) {
//        _accountsList.value?.run {
//            if (pageIndex * 20 < allcontacts.value?.data?.totalCount ?: 0)
//                _hotelSearchRequest.value = this.apply { pageIndex++ }
//        }

        //Get All Accounts

        generalListsRepository.filter(x,Filter_insert()).apply {
            observeForever {
                Log.d("fetchContacts", "fetchContacts2: " + it.data?.data)
                if (it.data != null) {
                    Log.d("fetchContacts", "fetchContacts: " + it.data.data)
                    _accountsList.value =
                        _accountsList.value!!.apply { clear() }
                    _accountsList.value =
                        _accountsList.value!!.apply { addAll(it.data.data) }
                }
            }
        }

    }
    fun Filter_insert()= Filter(
        assigned=true,
        statuses = emptyList()
    )
    fun total() {

        //Get All Accounts





        generalListsRepository.getAllAccounts().apply {
            observeForever {
                Log.d("fetchContacts", "fetchContacts2: "+it.data?.data)
                if (it.data != null) {
                    Log.d("fetchContacts", "fetchContacts: "+it.data.data)


                    _accountsList.value =
                        _accountsList.value!!.apply { clear() }
                    _accountsList.value =
                        _accountsList.value!!.apply { addAll(it.data.data) }
                }
            }
        }

    }
}