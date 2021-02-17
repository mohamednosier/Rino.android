package com.rinho.payment.data.repository

import com.rinho.payment.data.api.ApiService
import com.rinho.payment.di.NormalRequest
import com.rinho.payment.models.*
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Get Lookups data and manage its data sources.
 *
 * @property apiService [ApiService] the interface contains API end points.
 * @constructor create instance using dagger constructor injection.
 */
@ActivityScoped
class GeneralListsRepository @Inject constructor(@NormalRequest private val apiService: ApiService) {



//    fun getAllAccounts() =
//        object : NetworkOnlyResource<Accounts, GeneralResponse<Accounts>>() {
//            override fun createCall() = apiService.getAllaccounts()
//        }.asLiveData()
fun getAllAccounts() =
    object : NetworkOnlyResource<Accounts,Accounts>() {
        override fun createCall() = apiService.getAllaccounts()
    }.asLiveData()


    fun getAllDetailsAccounts() =
        object : NetworkOnlyResource<Accounts,Accounts>() {
            override fun createCall() = apiService.getAlldisplayAccounts()
        }.asLiveData()

    fun getAllAccounts1(orderId: String) =
        object : NetworkOnlyResource<Accounts, Accounts>() {
            override fun createCall() = apiService.getAllaccounts1(orderId)
        }.asLiveData()

    fun getAllApproveandDenyStatues(orderId: String,action: String) =
        object : NetworkOnlyResource<ApproveandDenyStatues, ApproveandDenyStatues>() {
            override fun createCall() = apiService.getApproveandDeny(orderId,action)
        }.asLiveData()
    fun search(orderId: String) =
        object : NetworkOnlyResource<Accounts, Accounts>() {
            override fun createCall() = apiService.getAllSearch(orderId)
        }.asLiveData()
    fun filter(orderId: String,sendFilter: Filter) =
        object : NetworkOnlyResource<Accounts, Accounts>() {
            override fun createCall() = apiService.getAllFilter(orderId,sendFilter)
        }.asLiveData()

    fun testNotification(hotelSearchRequest: SendNotification) =
        object :
            NetworkOnlyResource<NotificationStatues, NotificationStatues>() {
            override fun createCall() = apiService.testNotification(hotelSearchRequest)
        }.asLiveData()


//    fun getAllAccounts(email: String) =
//        object : NetworkOnlyResource<Accounts, Accounts>() {
//            override fun createCall() = apiService.getAllaccounts(
//                mapOf(
//
//                    "username" to email
//
//
//
//
//                    )
//            )
//        }.asLiveData()


    fun getAllDetails(orderId: String) =
        object : NetworkOnlyResource<Details, Details>() {
            override fun createCall() = apiService.getAllDetails(orderId)
        }.asLiveData()

    fun inserAccount(account: InsertAccounts) =
        object : NetworkOnlyResource<Any, GeneralResponse<Any>>() {
            override fun createCall() = apiService.insertaccount(account)
        }.asLiveData()


}