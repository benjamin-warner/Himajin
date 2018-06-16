package io.nihonkaeritai.himajin.Adapters

import android.content.Context
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
import io.nihonkaeritai.himajin.Auth.Auth
import io.nihonkaeritai.himajin.Interfaces.IAuth
import io.nihonkaeritai.himajin.Interfaces.IHandlesAuthentication
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

    class AuthFragment : Fragment() {
        private lateinit var email: EditText
        private lateinit var password: EditText
        private lateinit var authHandler: IHandlesAuthentication

        companion object {
            private const val NEW_USER: String = "NEW_USER"

            fun newInstance(newUser: Boolean): AuthFragment {
                val fragment = AuthFragment()
                val bundle = Bundle()
                bundle.putBoolean(NEW_USER, newUser)
                fragment.arguments = bundle
                return fragment
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = layoutInflater.inflate(R.layout.fragment_login, container, false)

            email = view.findViewById(R.id.editText_email)
            password = view.findViewById(R.id.editText_password)

            val title: TextView = view.findViewById(R.id.page_name)
            val isNewUser = arguments!!.getBoolean(NEW_USER)
            if (isNewUser) title.text = "Register" else title.text = "Login"

            val authButton: Button = view.findViewById(R.id.button_auth)
            if (isNewUser) authButton.text = "Register" else authButton.text = "Login"

            authButton.setOnClickListener { runAuth() }

            return view
        }

        override fun onAttach(context: Context?) {
            super.onAttach(context)
            if (context is IHandlesAuthentication){
                authHandler = context
            }
            else{
                throw ClassCastException("Context must implement IHandlesAuthentication!")
            }
        }

        private fun runAuth() {
            val email = email.text.toString()
            val password = password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val auth: IAuth = Auth()
                val isNewUser = arguments!!.getBoolean(NEW_USER)
                if (isNewUser) {
                    auth.register(email, password, authHandler)
                }
                else {
                    auth.login(email, password, authHandler)
                }
            }
        }
    }
}
