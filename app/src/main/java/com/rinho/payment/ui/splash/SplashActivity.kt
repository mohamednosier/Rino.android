package com.rinho.saudi2go.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rinho.saudi2go.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycleScope.launch(Dispatchers.IO) {
            //get stored lang (pre-selected lang)
//            val currentLang = Localization.getStoredLanguage(this@SplashActivity)

            /**
             * 1- is user selected language before? yes: go to step 2, no open [LanguageActivity]
             * 2- get stored user from db if null then user is not logged in open [AuthActivity],
             *   otherwise open [MainActivity]
             */
//            withContext(Dispatchers.Main) {
//                if (currentLang == UN_SET_LANG.langId)
//                    openActivity(LanguageActivity::class.java, true)
//                else {
//                    viewModel.getUserAuth()
//                    viewModel.launchDestination.observe(this@SplashActivity) {
//                        if (it == null)
//                            return@observe
//
//                        openActivity(
//                            if (it == AUTH_ACTIVITY) AuthActivity::class.java else MainActivity::class.java,
//                            true
//                        )
//                    }
//                }
//            }
        }
    }

}
