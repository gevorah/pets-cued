package co.edu.icesi.dev.petscued.view.pets

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentPetsBinding
import co.edu.icesi.dev.petscued.model.Publication
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_pets.*
import kotlin.collections.ArrayList

class PetsFragment : Fragment() {

    private lateinit var binding: FragmentPetsBinding
    private lateinit var publicationLayoutManager: GridLayoutManager
    private lateinit var petsPublicationAdapter: PetsPublicationAdapter
    private lateinit var publicationList: ArrayList<Publication>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPetsBinding.inflate(inflater, container, false)

        binding.adoptionPetsButton.setOnClickListener {
            binding.adoptionPetsButton.setBackgroundColor(Color.GREEN)
            binding.lostPetsButton.setBackgroundColor(Color.parseColor("#FFCF9E70"))
            filterPublicationListByStatus(Publication.LOST)
        }
        binding.lostPetsButton.setOnClickListener {
            binding.lostPetsButton.setBackgroundColor(Color.GREEN)
            binding.adoptionPetsButton.setBackgroundColor(Color.parseColor("#FFCF9E70"))
            filterPublicationListByStatus(Publication.ADOPTION)
        }
        binding.filterPetsButton.setOnClickListener {
            val publicationFilterFragment = PublicationFilterFragment()
            setFragment(publicationFilterFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.publicationLayoutManager = GridLayoutManager(context, 2)
        petsPublicationRecyclerView.layoutManager = publicationLayoutManager
        petsPublicationRecyclerView.setHasFixedSize(true)
        petsPublicationAdapter = PetsPublicationAdapter()
        petsPublicationRecyclerView.adapter = petsPublicationAdapter

        publicationList = ArrayList()
        loadPublicationsFromFirebase()
        binding.lostPetsButton.performClick()
    }

    private fun setFragment(fragment: Fragment) = requireActivity().supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        addToBackStack(null)
        commit()
    }

    private fun filterPublicationListByStatus(status: String) {
        val filteredPublicationList: List<Publication> = publicationList.filter { publication ->
            publication.status.equals(status, ignoreCase = true)
        }
        petsPublicationAdapter.setPublicationList(filteredPublicationList as ArrayList<Publication>)
    }

    private fun loadPublicationsFromFirebase(){
        Firebase.firestore.collection("publications").get().addOnCompleteListener{ task->
            for(doc in task.result!!){
                val publication = doc.toObject(Publication::class.java)
                publicationList.add(publication)
                Log.e(">>", publication.name)
            }
        }
        petsPublicationAdapter.setPublicationList(publicationList)
    }
}