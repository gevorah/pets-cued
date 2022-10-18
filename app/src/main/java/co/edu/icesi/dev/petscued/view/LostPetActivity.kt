package co.edu.icesi.dev.petscued.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.edu.icesi.dev.petscued.R
import kotlinx.android.synthetic.main.activity_lostpet.*
import kotlinx.android.synthetic.main.activity_petadoption.*

class LostPetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lostpet)

        lostPetBttn.setOnClickListener {

            val intent = Intent(this, PetsPublicationActivity::class.java )
            startActivity(intent)
        }
    }
}