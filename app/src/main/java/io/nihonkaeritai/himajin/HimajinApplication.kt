package io.nihonkaeritai.himajin

import android.app.Application
import android.content.Intent
import io.nihonkaeritai.himajin.Auth.FirebaseAuthMethod
import io.nihonkaeritai.himajin.Interfaces.IAuth

class HimajinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val auth : IAuth = FirebaseAuthMethod()
        if(auth.isLoggedIn())
            startActivity(Intent(this, MainActivity::class.java))
        else
            startActivity(Intent(this, AuthActivity::class.java))
    }
}