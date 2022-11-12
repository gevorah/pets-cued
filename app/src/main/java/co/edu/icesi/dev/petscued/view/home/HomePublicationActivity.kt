package co.edu.icesi.dev.petscued.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.model.Publication
import co.edu.icesi.dev.petscued.view.pets.AdoptionPetActivity
import java.util.*
import kotlin.collections.ArrayList

class HomePublicationActivity : AppCompatActivity() {

    private lateinit var publicationLayoutManager: GridLayoutManager
    private lateinit var homePublicationAdapter: HomePublicationAdapter
    private lateinit var publicationList: ArrayList<Publication>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
            "José Castro",
            "dog",
            "Perdido",
            "Cali, Santa Mónica",
            "7 años",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "Sí")
        )
        publicationList.add(
            Publication(
            UUID.randomUUID(),
            "path",
            "Cat",
            "Mestiza",
            "Male",
            "José Castro",
            "dog",
            "Adopción",
            "Cali, Santa Mónica",
            "7 años",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "Sí")
        )
        publicationList.add(
            Publication(
            UUID.randomUUID(),
            "path",
            "Duck",
            "Mestiza",
            "Male",
            "José Castro",
            "dog",
            "Adopción",
            "Cali, Santa Mónica",
            "7 años",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "Sí")
        )
        homePublicationAdapter.setPublicationList(publicationList)
    }
}