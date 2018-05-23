package io.nihonkaeritai.himajin.Auth

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.nihonkaeritai.himajin.Interfaces.IAuth

class FirebaseAuthMethod : IAuth, OnCompleteListener<AuthResult> {

    override fun onComplete(p0: Task<AuthResult>) {
        if(p0.isSuccessful)
            System.out.print("Auth Success")
        else
            Log.e("FB Auth Error!", p0.exception!!.localizedMessage)
    }

    override fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
    }

    override fun register(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(this)
    }

}