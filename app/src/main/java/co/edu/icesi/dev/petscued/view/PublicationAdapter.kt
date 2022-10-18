package co.edu.icesi.dev.petscued.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.model.Publication
import java.util.*
import kotlin.collections.ArrayList

class PublicationAdapter : RecyclerView.Adapter<PublicationView>() {

    private val publicationList = ArrayList<Publication>()

    init {
        publicationList.add(Publication(UUID.randomUUID(),
            "Laila",
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
            "Sí"))
        publicationList.add(Publication(UUID.randomUUID(),
            "Laila",
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
            "Sí"))
        publicationList.add(Publication(UUID.randomUUID(),
            "Laila",
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
            "Sí"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationView {
        var layoutInflater = LayoutInflater.from(parent.context)
        val publication = layoutInflater.inflate(R.layout.publication_buttons, parent, false)
        return PublicationView(publication)
    }

    override fun onBindViewHolder(holder: PublicationView, position: Int) {
        var publication = publicationList[position]
        holder.nameTextView.text = publication.name
        holder.breedTextView.text = publication.breed
        holder.ownerTextView.text = publication.owner
        holder.locationTextView.text = publication.location
    }

    override fun getItemCount(): Int {
        return publicationList.size
    }
}