package com.rinho.payment.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @author Wellington Costa on 30/12/2017.
 */
@Keep
data class Accounts (
//        @SerializedName("accounts") val accounts: List<Accounts1>,
//        @SerializedName("server_knowledge") val server_knowledge: Int
//) {
@SerializedName("data") val data: List<Accounts1>,
@SerializedName("total") val total: Int
) : IdentityGeneralResponse()

//}