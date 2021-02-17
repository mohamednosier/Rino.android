package com.rinho.payment.ui.Accounts.New_Account

import androidx.annotation.StringRes
import com.rinho.payment.R

/**
 * Enum of user gender.
 * @property value Int the id of the gender.
 * @property txtRes Int the string res of the gender
 */
enum class Type(val value: Int, @StringRes val txtRes: Int) {
    Checking(1, R.string.checking),
    Savings(2, R.string.saving),
    creditCard(3, R.string.creditcard)
}