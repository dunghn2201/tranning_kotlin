package com.dunghn.tranningkotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.dunghn.tranningkotlin.data.UserPreferences
import com.dunghn.tranningkotlin.ui.auth.AuthActivity
import com.dunghn.tranningkotlin.ui.home.HomeActivity
import com.dunghn.tranningkotlin.util.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
            Log.d("LOGGING", it ?: "Token is Null")
        })

    }
}