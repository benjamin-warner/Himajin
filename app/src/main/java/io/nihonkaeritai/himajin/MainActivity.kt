package io.nihonkaeritai.himajin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import io.nihonkaeritai.himajin.Auth.Auth
import io.nihonkaeritai.himajin.Fragments.DashboardFragment
import io.nihonkaeritai.himajin.Fragments.ProfileFragment
import io.nihonkaeritai.himajin.Interfaces.IAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = ProfileFragment.newInstance("Hey Home")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val fragment = DashboardFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val fragment = ProfileFragment.newInstance("Hey Notes")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        checkLogin()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_dashboard
    }

    private fun checkLogin() {
        val auth : IAuth = Auth()
        if(!auth.isLoggedIn()) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}
