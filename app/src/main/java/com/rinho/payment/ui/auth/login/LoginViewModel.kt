package com.rinho.saudi2go.ui.auth.login

import android.content.ContentValues
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.rinho.payment.data.repository.AuthRepository
import com.rinho.payment.models.SendNotification
import com.rinho.payment.util.Event
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * perform business logic and store ui states for [LoginFragment].
 */
class LoginViewModel @ViewModelInject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    /**
     * Pair of entered email and password entered by user.
     */
    private val userInfo: MutableLiveData<Pair<String, String>> = MutableLiveData()

    /**
     * android id .
     */
    private val androidId = MutableLiveData<String>()

    private val _notificationRequest = MutableLiveData<SendNotification>()

    /**
     * Immutable version of [_hotelSearchRequest].
     */
    val _notification = _notificationRequest as LiveData<SendNotification>

    /**
     *  event to navigate to the main activity after the login completed successfully and stored.
     */
    private val _navigateToMainActivity = MutableLiveData<Event<Unit>>()

    /**
     * Immutable version of [_navigateToMainActivity]
     */
    val navigateToMainActivity = _navigateToMainActivity as LiveData<Event<Unit>>



    private val _navigate = MutableLiveData<Event<Unit>>()

    /**
     * Immutable version of [_navigateToMainActivity]
     */
    val navigate = _navigate as LiveData<Event<Unit>>

    /**
     *  event to display error.
     */
    private val _error = MutableLiveData<Event<Unit>>()

    /**
     * Immutable version of [_navigateToMainActivity]
     */
    val error = _error as LiveData<Event<Unit>>

    /**
     * send login request with user inputs every update of [userInfo].
     */
    val loginResponse = userInfo.switchMap {
        authRepository.login(it.first, it.second)
    }.apply {
        observeForever { resource ->

            if (resource.data != null) {
                viewModelScope.launch(Dispatchers.IO) {
                    resource.data.run {
                        email = userInfo.value!!.first
                        password = userInfo.value!!.second
                        authRepository.insertUserAuth(this)

                        Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                                return@OnCompleteListener
                            }

                            // Get new FCM registration token
                            val token = task.result

                            // Log and toast
//                val msg = getString(R.string.msg_token_fmt, token)
                            Log.d(ContentValues.TAG, "FCM registration Token " +token)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                            if (token != null) {
                                Log.d(ContentValues.TAG, "FCM registration Token1 " )
                                initHotelSearchRequest(token)


//                                    authRepository.testNotification(initHotelSearchRequest(token).apply {
//                                        observeForever{
                                            _navigateToMainActivity.postValue(Event(Unit))
//                                        }
//
//
//
//                                    })
                            }
                        })
                    }
//                    token.onTokenRefresh()

                }
            }else if (resource.code ==400){
                _error.postValue(Event(Unit))
            }
        }
    }

    val notification_statues=   _notificationRequest.switchMap {
        authRepository.testNotification(it)
    }.apply {
        observeForever {
            if (it.data!=null) {
                _navigate.postValue(Event(Unit))
            }
        }
    }

    fun sendnotification(account: SendNotification) =
        authRepository.testNotification(account.apply {

        })

//    fun updateUserDatabase( authdata: UserAuth) {
//        authRepository.insertUserAuth(authdata)
//    }

    /**
     * store user inputs as [Pair] of two strings.
     *
     * @param email String entered email.
     * @param password String entered password.
     */
    fun updateUserInfo(
        email_edit: String,
        password_edit: String,
        android_Id: String
    ) {
        androidId.value=android_Id
        userInfo.value = Pair(email_edit, password_edit)
    }
    fun initHotelSearchRequest(token: String) = SendNotification(
        token = token,
        deviceid = androidId.toString()

    )
}