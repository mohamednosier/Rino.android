package com.rinho.payment.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SendNotification (
    @SerializedName("token") val token: String,
    @SerializedName("deviceid") val deviceid: String
    )