package com.rinho.saudi2go.data.api

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()


//        Amplify.addPlugin(AWSCognitoAuthPlugin())
//        Amplify.configure(applicationContext)
//        Amplify.Auth.signIn(
//            "mohamednosier123@gmail.com",
//            "P@ssw0rd2020",
//            { result ->  if (result.isSignInComplete) {
//
//                Amplify.Auth.fetchAuthSession(
//                    { result ->
//                       Log.i("AmplifyQuickstart", (result as AWSCognitoAuthSession).userPoolTokens.value?.accessToken.toString())
//                        Log.i("AmplifyQuickstart", Gson().toJson((result as AWSCognitoAuthSession).userPoolTokens.value))
//
//                        (result as AWSCognitoAuthSession).userPoolTokens.value?.run{
//                            val user = UserAuth(accessToken = this.accessToken, email = "", password = "")
//                        }
//
//                    },
//                    { error -> Log.e(
//                        "AmplifyQuickstartError", error.toString())
//                    }
//                )
//            } else "Sign in not complete" },
//            { error -> Log.e("AuthQuickstart", error.toString()) }
//        )


    }
public fun test(){

}
}