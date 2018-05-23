package io.nihonkaeritai.himajin.Adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.nihonkaeritai.himajin.Auth.FirebaseAuthMethod
import io.nihonkaeritai.himajin.Interfaces.IAuth
import io.nihonkaeritai.himajin.R

class AuthPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> AuthFragment.newInstance(false)
            1 -> AuthFragment.newInstance(true)
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    class AuthFragment : Fragment(){

        private lateinit var email : EditText
        private lateinit var password : EditText

        companion object {
            private const val NEW_USER : String = "NEW_USER"

            fun newInstance(newUser: Boolean): AuthFragment {
                val fragment = AuthFragment()
                val bundle = Bundle()
                bundle.putBoolean(NEW_USER, newUser)
                fragment.arguments = bundle
                return fragment
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = layoutInflater.inflate(R.layout.fragment_login, container,false)

            email = view.findViewById(R.id.editText_email)
            password = view.findViewById(R.id.editText_password)

            val isNewUser = arguments!!.getBoolean(NEW_USER)
            val title : TextView = view.findViewById(R.id.page_name)
            if(isNewUser) title.text = "Register" else title.text = "Login"

            val authButton : Button = view.findViewById(R.id.button_auth)
            if(isNewUser) authButton.text = "Register" else authButton.text = "Login"

            authButton.setOnClickListener {
                val email = email.text.toString()
                val password = password.text.toString()
                if(email.isNotEmpty() && password.isNotEmpty()) {
                    val auth: IAuth = FirebaseAuthMethod()
                    if(isNewUser) auth.register(email, password) else auth.login(email, password)
                }
            }
            return view
        }
    }
}
