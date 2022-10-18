package co.edu.icesi.dev.petscued.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import co.edu.icesi.dev.petscued.R
import kotlinx.android.synthetic.main.activity_publication.*

class PetsActivity : AppCompatActivity() {

    private lateinit var publicationLayoutManager: GridLayoutManager
    private lateinit var publicationAdapter: PublicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pets)

        publicationLayoutManager = GridLayoutManager(this, 2)
        publicationRecyclerView.layoutManager = publicationLayoutManager
        publicationRecyclerView.setHasFixedSize(true)

        publicationAdapter = PublicationAdapter()
        publicationRecyclerView.adapter = publicationAdapter
    }
}