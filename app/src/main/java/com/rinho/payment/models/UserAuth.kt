package com.rinho.payment.models


import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


@RealmClass
open class UserAuth(
    @SerializedName("access_token") var access_token: String = "",
    @SerializedName("expires_in") var expiresIn: Int = 0,
    @SerializedName("refresh_token") var refresh_token: String = "",
    @SerializedName("scope") var scope: String = "",

//    @SerializedName("token_type") var tokenType: String = "Bearer ",
//    var email: String = "",
//    var password: String = "",
//    var access_token: String = "",
    var email: String = "",
    var password: String = "",
) : RealmModel {

    @PrimaryKey
    var databaseID: Int = 1
}
