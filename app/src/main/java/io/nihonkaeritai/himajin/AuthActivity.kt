package io.nihonkaeritai.himajin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.widget.Toast
import io.nihonkaeritai.himajin.Adapters.AuthPagerAdapter
import io.nihonkaeritai.himajin.Exceptions.AuthException
import io.nihonkaeritai.himajin.Interfaces.IHandlesAuth

class AuthActivity : FragmentActivity(), IHandlesAuth {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewPager : ViewPager = findViewById(R.id.pager)
        val adapter = AuthPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    override fun handleAuthAttempt(success: Boolean, exception: AuthException?) {
        if(success) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else
            Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
    }
}
