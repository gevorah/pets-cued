package co.edu.icesi.dev.petscued.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.model.Publication
import co.edu.icesi.dev.petscued.view.PetsPublicationActivity
import co.edu.icesi.dev.petscued.view.pets.AdoptionPetActivity
import co.edu.icesi.dev.petscued.view.pets.LostPetActivity
import co.edu.icesi.dev.petscued.view.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomePublicationActivity : AppCompatActivity() {

    private lateinit var publicationLayoutManager: GridLayoutManager
    private lateinit var homePublicationAdapter: HomePublicationAdapter
    private lateinit var publicationList: ArrayList<Publication>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    finish()
                    overridePendingTransition(0, 0);
                    startActivity(intent)
                    overridePendingTransition(0, 0);
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_pets -> {
                    startActivity(Intent(this, PetsPublicationActivity::class.java))
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }

        this.publicationLayoutManager = GridLayoutManager(this, 2)
        homePublicationRecyclerView.layoutManager = publicationLayoutManager
        homePublicationRecyclerView.setHasFixedSize(true)

        homePublicationAdapter = HomePublicationAdapter()
        homePublicationRecyclerView.adapter = homePublicationAdapter

        addHardcodedElements()

        adoptionImageButton.setOnClickListener{
            val intent = Intent(this, AdoptionPetActivity::class.java)
            startActivity(intent)
        }

        lostImageButton.setOnClickListener {
            val intent = Intent(this, LostPetActivity::class.java )
            startActivity(intent)
        }
    }

    private fun addHardcodedElements() {
        this.publicationList = ArrayList()
        publicationList.add(
            Publication(
            UUID.randomUUID(),
            "path",
            "Laila",
            "Mestiza",
            "Male",
            "Jos?? Castro",
            "dog",
            "Perdido",
            "Cali, Santa M??nica",
            "7 a??os",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "S??")
        )
        publicationList.add(
            Publication(
            UUID.randomUUID(),
            "path",
            "Cat",
            "Mestiza",
            "Male",
            "Jos?? Castro",
            "dog",
            "Adopci??n",
            "Cali, Santa M??nica",
            "7 a??os",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "S??")
        )
        publicationList.add(
            Publication(
            UUID.randomUUID(),
            "path",
            "Duck",
            "Mestiza",
            "Male",
            "Jos?? Castro",
            "dog",
            "Adopci??n",
            "Cali, Santa M??nica",
            "7 a??os",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "S??")
        )
        homePublicationAdapter.setPublicationList(publicationList)
    }
}