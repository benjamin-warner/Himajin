package nihonkaeritai.com.himajin

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import nihonkaeritai.com.himajin.Adapters.AuthPagerAdapter

class AuthActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewPager : ViewPager = findViewById(R.id.pager)

        val adapter = AuthPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }
}