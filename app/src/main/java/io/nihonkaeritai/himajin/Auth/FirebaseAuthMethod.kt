package io.nihonkaeritai.himajin.Auth

import com.google.firebase.auth.FirebaseAuth
import io.nihonkaeritai.himajin.Exceptions.AuthException
import io.nihonkaeritai.himajin.Interfaces.IAuth
import io.nihonkaeritai.himajin.Interfaces.IHandlesAuth

class FirebaseAuthMethod : IAuth{

    override fun getEmail(): String? {
        return FirebaseAuth.getInstance().currentUser!!.email
    }

    override fun getUserId(): String? {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }


    override fun isLoggedIn() : Boolean{
        return FirebaseAuth.getInstance().currentUser != null
    }

    override fun login(email: String, password: String, handler: IHandlesAuth) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener({ result ->
                    handler.handleAuthAttempt(result.isSuccessful, AuthException(result.exception?.message!!))
        })
    }

    override fun register(email: String, password: String, handler: IHandlesAuth) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener({ result ->
                    if(result.isSuccessful){
                        handler.handleRegisterAttempt(result.isSuccessful, null)
                    }

        })
    }
}
