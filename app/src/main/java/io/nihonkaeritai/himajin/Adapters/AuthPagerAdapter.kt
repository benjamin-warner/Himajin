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
            0 -> AuthFragment.newInstance("Login")
            1 -> AuthFragment.newInstance("Register")
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
            fun newInstance(name: String): AuthFragment {
                val fragment = AuthFragment()
                val bundle = Bundle()
                bundle.putString("NAME", name)
                fragment.arguments = bundle
                return fragment
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = layoutInflater.inflate(R.layout.fragment_login, container,false)
            val text : TextView = view.findViewById(R.id.page_name)
            val title = arguments!!.getString("NAME")
            text.text = title

            val authButton : Button = view.findViewById(R.id.button_auth)
            authButton.text = title

            email = view.findViewById(R.id.editText_email)
            password = view.findViewById(R.id.editText_password)

            authButton.setOnClickListener {
                val email = email.text.toString()
                val password = password.text.toString()
                if(email.isNotEmpty() && password.isNotEmpty()) {
                    val auth: IAuth = FirebaseAuthMethod()
                    auth.login(email, password)
                }
            }

            return view
        }
    }
}
