package com.rinho.payment.models

import com.google.gson.annotations.SerializedName

/**
 * @author Wellington Costa on 30/12/2017.
 */
//@Keep
data class DisplayAccounts (
//        @SerializedName("accounts") val accounts: List<Accounts1>,
//        @SerializedName("server_knowledge") val server_knowledge: Int
//) {
@SerializedName("data") val data: List<DisplayAccounts1>,

) : IdentityGeneralResponse()

//}