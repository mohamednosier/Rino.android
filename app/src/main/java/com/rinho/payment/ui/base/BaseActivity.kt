package com.rinho.payment.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity(), BaseView {


    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        //update the localization of the activity

        super.onCreate(savedInstanceState)
        initBindingLifeCycleOwner()
        init()
    }






    override fun initBindingLifeCycleOwner() {
        binding.lifecycleOwner = this
    }

}





