package nihonkaeritai.com.himajin.Adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import nihonkaeritai.com.himajin.R

class AuthPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){

    private val pages: SparseArray<AuthFragment>

    init {
        pages = SparseArray(count)
    }

    override fun getItem(position: Int): Fragment? {
        val fragment : AuthFragment? = pages.get(position);
        if(fragment is AuthFragment){
            return fragment
        }
        when (position) {
            0 -> {
                val loginFragment = AuthFragment.newInstance("Login")
                pages.append(position, loginFragment)
                return loginFragment
            }
            1 -> {
                val registerFragment = AuthFragment.newInstance("Register")
                pages.append(position, registerFragment)
                return registerFragment
            }
        }
        return null
    }

    override fun getCount(): Int {
        return 2
    }

    class AuthFragment : Fragment(){

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
            val text : TextView = view!!.findViewById(R.id.page_name)
            val title = arguments!!.getString("NAME")
            text.text = title
            return view
        }
    }
}