package com.rinho.saudi2go.ui.auth


import com.rinho.payment.R
import com.rinho.payment.databinding.ActivityAuthBinding
import com.rinho.payment.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    override fun getLayoutRes() = R.layout.activity_auth

    override fun init() {

    }
}