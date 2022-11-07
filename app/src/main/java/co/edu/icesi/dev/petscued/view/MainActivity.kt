package co.edu.icesi.dev.petscued.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.view.home.HomeFragment
import co.edu.icesi.dev.petscued.view.home.HomePublicationActivity
import co.edu.icesi.dev.petscued.view.login.LoginActivity
import co.edu.icesi.dev.petscued.view.profile.ProfileActivity
import co.edu.icesi.dev.petscued.view.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()

        setCurrentFragment(homeFragment)

        bottom_navigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    setCurrentFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_pets -> {

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    setCurrentFragment(profileFragment)
                    return@setOnItemSelectedListener true
                }
            }
            true
        }
        //val intent = Intent(this, LoginActivity::class.java)
        //startActivity(intent)
    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
    }

}