package nihonkaeritai.com.himajin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nihonkaeritai.com.himajin.Fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = ProfileFragment.newInstance("Hey Home")
                supportFragmentManager.beginTransaction().replace(R.id.framgent_container, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val fragment = ProfileFragment.newInstance("Hey DB")
                supportFragmentManager.beginTransaction().replace(R.id.framgent_container, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val fragment = ProfileFragment.newInstance("Hey Notes")
                supportFragmentManager.beginTransaction().replace(R.id.framgent_container, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_dashboard
    }
}
