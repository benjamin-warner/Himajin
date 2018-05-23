package io.nihonkaeritai.himajin.Auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.nihonkaeritai.himajin.Exceptions.AuthException
import io.nihonkaeritai.himajin.Interfaces.IAuth
import io.nihonkaeritai.himajin.Interfaces.IHandlesAuth

class FirebaseAuthMethod : IAuth{

    override fun isLoggedIn() : Boolean{
        return FirebaseAuth.getInstance().currentUser != null
    }

    override fun login(email: String, password: String, handler: IHandlesAuth) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener({ result ->
                    handleResult(result, handler)
        })
    }

    override fun register(email: String, password: String, handler: IHandlesAuth) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener({ result ->
                    handleResult(result, handler)
        })
    }

    private fun handleResult(p0: Task<AuthResult>, handler: IHandlesAuth) {
        if(p0.isSuccessful)
            handler.handleAuthAttempt(true, null)
        else
            handler.handleAuthAttempt(false, AuthException(p0.exception?.message!!))
    }
}
