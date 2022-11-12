package co.edu.icesi.dev.petscued.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.view.home.HomeFragment
import co.edu.icesi.dev.petscued.view.login.LoginActivity
import co.edu.icesi.dev.petscued.view.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()

        setFragment(homeFragment)

        bottom_navigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    setFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_pets -> {

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    setFragment(profileFragment)
                    return@setOnItemSelectedListener true
                }
            }
            true
        }
        //val intent = Intent(this, LoginActivity::class.java)
        //startActivity(intent)
    }

    private fun badgeSetup(id: Int, alerts: Int) {
        val badge = bottom_navigation.getOrCreateBadge(id)
        badge.isVisible = true
        badge.number = alerts
    }

    private fun badgeClear(id: Int) {
        val badgeDrawable = bottom_navigation.getBadge(id)
        if (badgeDrawable != null) {
            badgeDrawable.isVisible = false
            badgeDrawable.clearNumber()
        }
    }

    private fun setFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        addToBackStack(null)
        commit()
    }

}