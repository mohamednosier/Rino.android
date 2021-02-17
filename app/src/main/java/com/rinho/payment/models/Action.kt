package com.rinho.payment.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Action (
    @SerializedName("name") val name: String
//    @SerializedName("date") val date: String,
)