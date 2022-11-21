package co.edu.icesi.dev.petscued.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.model.Publication
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlin.collections.ArrayList

class HomePublicationAdapter : RecyclerView.Adapter<HomePublicationView>(){

    private var publicationList = ArrayList<Publication>()

    fun setPublicationList(publicationList: ArrayList<Publication>){
        this.publicationList = publicationList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePublicationView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val publication = layoutInflater.inflate(R.layout.publication, parent, false)
        return HomePublicationView(publication)
    }

    override fun onBindViewHolder(holder: HomePublicationView, position: Int) {
        val publication: Publication = publicationList[position]
        holder.publication = publication
        Firebase.storage.reference.child("publications").child(publication.image).downloadUrl.addOnSuccessListener {
            Glide.with(holder.petImageView).load(it).into(holder.petImageView)
        }
        holder.nameTextView.text = publication.name
        holder.breedTextView.text = publication.breed
        holder.ownerTextView.text = publication.owner
        holder.locationTextView.text = publication.location
    }

    override fun getItemCount(): Int {
        return publicationList.size
    }
}