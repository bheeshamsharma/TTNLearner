package com.psgpw.geek_ttn.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.psgpw.geek_ttn.R
import com.psgpw.pickapp.data.DataStoreManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            delay(2000L)
            lifecycleScope.launch {
                DataStoreManager(this@SplashActivity).isUserLogin().collect { loginStatus ->
                    if (loginStatus) {
                        startActivity(
                            Intent(
                                this@SplashActivity,
                                MainActivity::class.java
                            )
                        )
                        finish()
                    } else {
                        startActivity(
                            Intent(
                                this@SplashActivity,
                                LoginActivity::class.java
                            )
                        )
                        finish()
                    }
                }
            }
            finish()
        }
    }
}