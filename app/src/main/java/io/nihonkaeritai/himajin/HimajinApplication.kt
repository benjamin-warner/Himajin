package io.nihonkaeritai.himajin

import android.app.Application
import com.google.firebase.FirebaseApp

class HimajinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}