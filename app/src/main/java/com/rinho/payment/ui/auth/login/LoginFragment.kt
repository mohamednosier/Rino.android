package com.rinho.saudi2go.ui.auth.login

import android.content.ContentValues.TAG
import android.provider.Settings.Secure
import android.util.Log
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.observe
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.rinho.databinding_validation.validate
import com.rinho.payment.R
import com.rinho.payment.data.db.queryFirst
import com.rinho.payment.databinding.FragmentLoginBinding
import com.rinho.payment.models.UserAuth
import com.rinho.payment.ui.base.BaseFragment
import com.rinho.payment.ui.common.DialogType
import com.rinho.payment.ui.common.GeneralDialog
import com.rinho.payment.ui.main.MainActivity
import com.rinho.payment.util.EventObserver
import com.rinho.payment.util.extensions.openActivity
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import org.json.JSONObject
import kotlin.jvm.Throws


/**
 * Display login UI form.
 */
@AndroidEntryPoint
class LoginFragment :
    BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class.java),
    ViewTreeObserver.OnGlobalLayoutListener {
    var android_id = ""
    var x:Int=0


    override fun getLayoutRes() = R.layout.fragment_login

    override fun init() {
        logout()
        initScrollChildToMathScreenSize()
        initSignIn()
      // initSignUp()
     //   initForgetPass()
//        val id: String = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

    }


    /**
     * add view observer to add margin to footer img if it's not aligned with screen bottom.
     */
    private fun initScrollChildToMathScreenSize() {

        if (binding.loginForm.viewTreeObserver.isAlive) {
            binding.loginForm.viewTreeObserver.addOnGlobalLayoutListener(this@LoginFragment)
        }
    }


    /**
     * - validate on login with amazon form whe login btn clicked.
     * - onValid send login request.
     * - handle the request states and if success navigate to [MainActivity].
     * if request is reqistered before but not complete  navigate to [Confirm Signup].
     */
    fun initSignIn() {
        android_id = Secure.getString(context?.contentResolver, Secure.ANDROID_ID)
        Log.d(TAG, "initSignIn: "+android_id)
        viewModel.loginResponse.observe(viewLifecycleOwner, ::handleApiStatus)
//        viewModel.notification_statues.observe(viewLifecycleOwner, ::handleApiStatus)
//
        viewModel.navigate.observe(viewLifecycleOwner, EventObserver {
            openActivity(MainActivity::class.java, true)
        })
        viewModel.navigateToMainActivity.observe(viewLifecycleOwner, EventObserver {
//            viewModel.sendnotification()
//                .observe(viewLifecycleOwner) {
//                    handleApiStatus(it)
//                    if (it.status == Status.SUCCESS){
//
//                        Log.d("initCreate", "initCreate: "+"true")
//
//                }else{
//                Log.d("initCreate", "initCreate: " + "false")
//            }
//            }
            Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log and toast
//                val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, "FCM registration Token " +token)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                if (token != null) {
                    Log.d(TAG, "FCM registration Token1 " )
//                    viewModel.notification_statues.observe(viewLifecycleOwner, ::handleApiStatus)

                    getUsers(token,android_id)

                }
            })


        })


        viewModel.error.observe(viewLifecycleOwner, EventObserver {
            if(x==1){

            }else {
                handle401Error()
            }
        })
        binding.btnLogin.root.setOnClickListener {
            if (binding.root.validate()) {
                viewModel.updateUserInfo(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    android_id
                )
                binding.setVariable(BR.isLoading, true)
            }else{
//                binding.setVariable(BR.isLoading, false)
            }



//            Amplify.Auth.signOut(
//                { Log.i("AuthQuickstart", "Signed out successfully") },
//                { error -> Log.e("AuthQuickstart", error.toString()) }
//            )


//
//            ConfirmSignup.newInstance(binding.etEmail.text.toString())
//                .apply { isCancelable = false }
//                .show(childFragmentManager, ConfirmSignup.TAG)

//            val user = UserAuth(
//                                        accessToken = "eyJraWQiOiJLam83b0JkSzFLV0V5ZW9cL0dnazhVemNVaXdcL3VrTHFZZDhYbklcL1g0V0JvPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJjMzllMGUyMS05OWUwLTQwN2ItYjEyOS03ZjI4YjYyODM1NDQiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiY3VzdG9tOkNvdW50cnlJZCI6IjEiLCJjdXN0b206SWJhbk51bWJlciI6IlNBMTc5MDk0MTMyNzExMTAwMDAwMDAwMiIsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTIuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0yXzdDRnJackhDdCIsInBob25lX251bWJlcl92ZXJpZmllZCI6ZmFsc2UsImNvZ25pdG86dXNlcm5hbWUiOiJtb2hhbWVkbm9zaWVyMTIzQGdtYWlsLmNvbSIsImF1ZCI6IjExMnVwY25raTUwOHA4cG1hcmlnYjRkc3VoIiwiY3VzdG9tOkNvbXBhbnlOYW1lIjoiRGV2b3Bzb2x1dGlvbSIsImV2ZW50X2lkIjoiZTFiMzZiZjAtMWFkMi00MDIyLThiODUtZjBjNzZkZTNmYjJmIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE2MDk3MjI3NjIsIm5hbWUiOiJtb2hhbWVkIG5vc2llciIsInBob25lX251bWJlciI6IisxMDEyODYwNzIzODUiLCJleHAiOjE2MDk3MjQwNDUsImlhdCI6MTYwOTcyMzc0NSwiZW1haWwiOiJtb2hhbWVkbm9zaWVyMTIzQGdtYWlsLmNvbSJ9.diL1plue0K2j_eaLN6O0yOKPslBZ-azuFw0rMYvLIrwo9ULyPhb9LikTJAoNghoF9rpNSp1RHh6mEQXcJeO9UFSmUsGa3vNLApjlNpHuk5u7yhUBNNt6cbYUbLfQ8ujelKKaAvnmNcVg1uoEKbAYCenrnDM6dQWX0ajRI_6OPgA5mHZsC5loQXDjdg88qu942ab13cDL3V134P6t4MVR0KptRswtWwtkMp1GZhPQBUD2CG7ez72rVmpCZA4zqRVZtNOTzbz53mV49A0OboiP_zJWJGWG85cI61R9tq7dlhV2JJFZNHYp6yhdoQP8CBPztjcBCeWJ7qmbFmYSflx09A",
//                                        email = binding.etEmail.text.toString(),
//                                        password = binding.etPassword.text.toString()
//                                    )
//                                    //insert into database userauth )access token,email,password)
//                                    viewModel.updateUserDatabase(user)
//
//                                      //  _navigateToMainActivity.postValue(Event(Unit))
//                                    openActivity(MainActivity::class.java, true)
        }
    }

    /**
     * override 401 error handling in login request to show error msg with invalid inputs.
     */
    fun handle401Error() {
x=x+1
        GeneralDialog(DialogType.ERROR, getString(R.string.invalid_login_data)).show(
            childFragmentManager,
            tag
        )
    }

    /**
     * init registerTxt click. open register screen on click.
     */
//    private fun initSignUp() {
 //       binding.btnRegister.setOnClickListener {
 //           findNavController().navigate(R.id.openRegister)
 //       }
 //   }

    /**
     * init forgetPass click. open forgetPass screen on click.
     */
 //   private fun initForgetPass() {
 //       binding.btnForgetPass.setOnClickListener {
 //           findNavController().navigate(R.id.openForgetPass)
 //       }
  //  }

    /**
     * when view draw on screen and not fill the screen add top margin to the footer
     * to make it align the screen bottom.
     */
    override fun onGlobalLayout() {
        if (binding.loginForm.height < binding.root.height) {
            binding.imgFooter.layoutParams =
                (binding.imgFooter.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    topMargin = binding.root.height - binding.loginForm.height
                }
        }

        if (binding.loginForm.viewTreeObserver.isAlive)
            binding.loginForm.viewTreeObserver.removeOnGlobalLayoutListener(this@LoginFragment)
    }

    fun logout() {
        Realm.getDefaultInstance().use { it.executeTransaction { it.deleteAll() } }
    }



    fun getUsers(token: String,deviceid: String) {
        // Instantiate the RequestQueue.
        Log.d(TAG, "getUsers: "+token +"    "+deviceid)
//        val requestBody = "token="+token + "&deviceid="+deviceid
        val jsonObject = JSONObject()
        jsonObject.put("token", token)
        jsonObject.put("deviceid", deviceid)
      val requestBody = jsonObject.toString();
        val queue = Volley.newRequestQueue(activity)
        val url: String = "https://rhino-identity.azurewebsites.net/api/notifications/mobile/add"

        // Request a string response from the provided URL.
        val jsonOblect = object : JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            Response.Listener {response ->
                openActivity(MainActivity::class.java, true)
                Log.d(TAG, "getUsers: "+response)


            },
            Response.ErrorListener { error ->  error.printStackTrace()}

        )
        {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {

                val headers: MutableMap<String, String> = HashMap()
                // Add your Header paramters here
                headers["Authorization"] ="Bearer  "+getUserToken()
                return headers
            }
//            override fun getBodyContentType(): String {
//                return "application/json"
//            }
//override fun getBody(): ByteArray {
//    return requestBody.toByteArray(Charset.defaultCharset())
//}

//            override fun getBody(): ByteArray {
//                val params2 = HashMap<String, String>()
//                params2.put("token",token )
//                params2.put("deviceid", deviceid)
//                return JSONObject(params2 as Map<*, *>).toString().toByteArray()
//            }

        }


        queue.add(jsonOblect)
    }
    fun getUserAuth() = queryFirst<UserAuth>()
    fun getUserToken() = getUserAuth()?.access_token

}