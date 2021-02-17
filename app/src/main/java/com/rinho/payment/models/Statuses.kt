package com.rinho.payment.models

import androidx.annotation.Keep

@Keep
data class Statuses(
    val assigned: Boolean=true,
    val statuses: List<Any>
)