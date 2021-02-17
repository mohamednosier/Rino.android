package com.rinho.payment.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Details1(
    @SerializedName("id") val id: String,
    @SerializedName("date") val date: String,
    @SerializedName("department") val department: String,
    @SerializedName("provision") val provision: String,
    @SerializedName("beneficiary") val beneficiary: String,
    @SerializedName("amount") val amount: Int,
@SerializedName("limit") val limit: Int,
@SerializedName("balance") val balance: String,
@SerializedName("desc") val desc: String,
@SerializedName("paytype") val paytype: String,
@SerializedName("status") val status: String,
    @SerializedName("action") val action: Action


//    @SerializedName("on_budget") val on_budget: Boolean,
//    @SerializedName("closed") val closed: Boolean,
//    @SerializedName("note") val note: String,
//    @SerializedName("balance") val balance: Int,
//    @SerializedName("cleared_balance") val cleared_balance: Int,
//    @SerializedName("uncleared_balance") val uncleared_balance: Int,
//    @SerializedName("transfer_payee_id") val transfer_payee_id: String,
//    @SerializedName("deleted") val deleted: Boolean,



//    @SerializedName("PRQ_DATE") val PRQ_DATE: String,
//@SerializedName("PRQ_ID") val PRQ_ID: Int,
//@SerializedName("PRQ_Dept") val PRQ_Dept: String,
//@SerializedName("PRQ_AMOUNT") val PRQ_AMOUNT: Int,
//@SerializedName("PRQ_SSP_CODE") val PRQ_SSP_CODE: Int,
//@SerializedName("STATUS") val STATUS: String




)