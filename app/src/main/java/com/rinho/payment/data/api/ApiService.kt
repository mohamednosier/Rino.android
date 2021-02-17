package com.rinho.payment.data.api

import androidx.lifecycle.LiveData
import com.rinho.payment.models.*
import retrofit2.http.*

/**
 * All end points for the app.
 */
interface ApiService {


//    @GET("budgets/2166dd29-d00c-46b5-a272-89ddaf999dee/accounts")
//    fun getAllaccounts(): LiveData<ApiResponse<GeneralResponse<Accounts>>>
    @GET("api/requests")
    fun getAllaccounts(): LiveData<ApiResponse<Accounts>>
    @GET("api/requests")
    fun getAlldisplayAccounts(): LiveData<ApiResponse<Accounts>>

    @GET("api/requests/{orderId}")
    fun getAllaccounts1(@Path("orderId") orderId: String): LiveData<ApiResponse<Accounts>>

    @POST("api/requests/action/{orderId}/{action}")
    fun getApproveandDeny(@Path("orderId") orderId: String,@Path("action") action: String): LiveData<ApiResponse<ApproveandDenyStatues>>

    @GET("api/requests/search/{orderId}")
    fun getAllSearch(@Path("orderId") orderId: String ): LiveData<ApiResponse<Accounts>>

    @POST("api/requests/filter/{orderId}")
    fun getAllFilter(@Path("orderId") orderId: String,@Body sendFilter: Filter): LiveData<ApiResponse<Accounts>>

    @POST("api/notifications/mobile/add")
    fun testNotification(@Body hotelsSearchRequest: SendNotification): LiveData<ApiResponse<NotificationStatues>>

    @GET("api/requests/{orderId}/details")
    fun getAllDetails(@Path("orderId") orderId: String): LiveData<ApiResponse<Details>>

    @POST("budgets/31594a4c-a709-4467-b83b-1dad94faf7fe/accounts")
    fun insertaccount(@Body account: InsertAccounts): LiveData<ApiResponse<GeneralResponse<Any>>>

    @FormUrlEncoded
    @POST("connect/token")
    fun login(@FieldMap body: Map<String, String>): LiveData<ApiResponse<UserAuth>>

//    @FormUrlEncoded
//    @POST("api/requests")
//    fun getAllaccounts(@FieldMap body: Map<String, String>): LiveData<ApiResponse<Accounts>>
}