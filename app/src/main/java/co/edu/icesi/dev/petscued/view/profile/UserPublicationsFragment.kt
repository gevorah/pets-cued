package co.edu.icesi.dev.petscued.view.profile

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.icesi.dev.petscued.databinding.FragmentUserPublicationsBinding
import co.edu.icesi.dev.petscued.model.Publication
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_user_publications.*
import java.util.*
import kotlin.collections.ArrayList

class UserPublicationsFragment : Fragment() {

    private lateinit var binding: FragmentUserPublicationsBinding
    private lateinit var publicationLayoutManager: LinearLayoutManager
    private lateinit var userPublicationAdapter: UserPublicationAdapter
    private lateinit var userPublicationList: ArrayList<Publication>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentUserPublicationsBinding.inflate(inflater, container, false)
        binding.lostPublicationButton.setOnClickListener {
            binding.lostPublicationButton.setBackgroundColor(Color.GREEN)
            binding.adoptionPublicationButton.setBackgroundColor(Color.parseColor("#FFCF9E70"))
            filterPublicationListByStatus(Publication.LOST)
        }
        binding.adoptionPublicationButton.setOnClickListener {
            binding.adoptionPublicationButton.setBackgroundColor(Color.GREEN)
            binding.lostPublicationButton.setBackgroundColor(Color.parseColor("#FFCF9E70"))
            filterPublicationListByStatus(Publication.ADOPTION)
        }
        binding.addFloatingActionButton.setOnClickListener {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        publicationLayoutManager = LinearLayoutManager(context)
        userPublicationRecyclerView.layoutManager = publicationLayoutManager
        userPublicationRecyclerView.setHasFixedSize(true)
        userPublicationAdapter = UserPublicationAdapter()
        userPublicationRecyclerView.adapter = userPublicationAdapter
        userPublicationList = ArrayList()
        loadUserPublicationsFromFirebase()
        binding.lostPublicationButton.performClick()
    }

    private fun filterPublicationListByStatus(status: String){
        val filteredPublicationList: List<Publication> = userPublicationList.filter {
                publication -> publication.status.equals(status, ignoreCase = true)
        }
        userPublicationAdapter.setPublicationList(filteredPublicationList as ArrayList<Publication>)
    }

    private fun loadUserPublicationsFromFirebase(){
        Firebase.firestore.collection("publications").get().addOnCompleteListener{ task->
            for(doc in task.result!!){
                val publication = doc.toObject(Publication::class.java)
                userPublicationList.add(publication)
                Log.e(">>", publication.name)
            }
        }
        userPublicationAdapter.setPublicationList(userPublicationList)
    }

    private fun addHardcodedElements() {
        userPublicationList.add(Publication(
            UUID.randomUUID().toString(),
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
            "Sí"))
        userPublicationList.add(Publication(
            UUID.randomUUID().toString(),
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
            "Sí"))
        userPublicationList.add(Publication(
            UUID.randomUUID().toString(),
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
            "Sí"))
    }
}