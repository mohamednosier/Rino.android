package com.rinho.payment.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.activity.viewModels
import com.rinho.payment.R
import com.rinho.payment.databinding.ActivityMainBinding
import com.rinho.payment.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {



    private val viewModel by viewModels<MainViewModel>()


    override fun getLayoutRes() = R.layout.activity_main

    override fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = "id"
            val channelName = "name"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW)
            )
        }

    }








}