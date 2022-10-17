package co.edu.icesi.dev.petscued.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.icesi.dev.petscued.R
import kotlinx.android.synthetic.main.activity_publication.*

class PublicationActivity : AppCompatActivity() {

    private lateinit var publicationLayoutManager: LinearLayoutManager
    private lateinit var publicationAdapter: PublicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)

        publicationLayoutManager = LinearLayoutManager(this)
        publicationRecyclerView.layoutManager = publicationLayoutManager
        publicationRecyclerView.setHasFixedSize(true)

        publicationAdapter = PublicationAdapter()
        publicationRecyclerView.adapter = publicationAdapter
    }
}