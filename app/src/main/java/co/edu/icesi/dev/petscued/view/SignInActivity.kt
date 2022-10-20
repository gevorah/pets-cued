package co.edu.icesi.dev.petscued.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.edu.icesi.dev.petscued.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.top_nav.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        back_title.setText(R.string.action_sign_in)
        back_btn.setOnClickListener {
            finish()
        }

        sign_in_btn.setOnClickListener {
            val intent = Intent(this, HomePublicationActivity::class.java)
            startActivity(intent)
        }

        //lost_password_txt.setOnClickListener {
        //    val intent = Intent(this, Activity::class.java)
        //    startActivity(intent)
        //}
    }
}