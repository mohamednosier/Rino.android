package com.rinho.saudi2go.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.rinho.saudi2go.ui.splash.LaunchDestination.AUTH_ACTIVITY
import com.rinho.saudi2go.ui.splash.LaunchDestination.MAIN_ACTIVITY
import com.rinho.payment.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(private val authRepository: AuthRepository) :
    ViewModel() {


    /**
     * The destination that user should go to.
     */
    private val _launchDestination = MutableLiveData<LaunchDestination>()

    /**
     * Immutable version of [_launchDestination]
     */
    val launchDestination = _launchDestination as LiveData<LaunchDestination>

    /**
     * get the stored user from the db then update [_launchDestination] with the destination that user should go to
     */
    fun getUserAuth() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = authRepository.getRegisteredUser()

            _launchDestination.postValue(if (user == null) AUTH_ACTIVITY else MAIN_ACTIVITY)
        }
    }
}

/**
 * The enum of destinations that works as entry points for the app.
 */
enum class LaunchDestination {
    AUTH_ACTIVITY,
    MAIN_ACTIVITY
}
