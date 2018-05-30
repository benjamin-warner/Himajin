package io.nihonkaeritai.himajin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.widget.Toast
import io.nihonkaeritai.himajin.Adapters.AuthPagerAdapter
import io.nihonkaeritai.himajin.Auth.FirebaseAuthMethod
import io.nihonkaeritai.himajin.DBModels.FirebaseUserModel
import io.nihonkaeritai.himajin.Exceptions.AuthException
import io.nihonkaeritai.himajin.Interfaces.IAuth
import io.nihonkaeritai.himajin.Interfaces.IHandlesAuth
import io.nihonkaeritai.himajin.Interfaces.IUser
import io.nihonkaeritai.himajin.Interfaces.IUserRepository
import io.nihonkaeritai.himajin.Repositories.FirebaseUserRepository

class AuthActivity : FragmentActivity(), IHandlesAuth {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewPager : ViewPager = findViewById(R.id.pager)
        val adapter = AuthPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    override fun handleRegisterAttempt(success: Boolean, exception: AuthException?) {
        if(success){
            createUserAccount()
            startMainActivity()
        }
        else{
            displayError(exception)
        }
    }

    override fun handleAuthAttempt(success: Boolean, exception: AuthException?) {
        if(success)
            startMainActivity()
        else
            displayError(exception)
    }

    private fun displayError(exception: AuthException?) {
        Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun createUserAccount(){
        val userRepository: IUserRepository = FirebaseUserRepository()
        val auth: IAuth = FirebaseAuthMethod()
        val newUser: IUser = FirebaseUserModel(auth.getUserId(), auth.getEmail())
        userRepository.addNewUser(newUser)
    }

    private fun startMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
