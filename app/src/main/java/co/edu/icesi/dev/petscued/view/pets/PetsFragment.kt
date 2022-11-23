package co.edu.icesi.dev.petscued.view.pets

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentPetsBinding
import co.edu.icesi.dev.petscued.model.Publication
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_pets.*

class PetsFragment : Fragment() {

    private lateinit var binding: FragmentPetsBinding
    private lateinit var publicationLayoutManager: GridLayoutManager
    private lateinit var petsPublicationAdapter: PetsPublicationAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPetsBinding.inflate(inflater, container, false)

        binding.adoptionPetsButton.setOnClickListener {
            fetchPublicationsByStatus(Publication.ADOPTION)
            binding.adoptionPetsButton.setBackgroundColor(Color.GREEN)
            binding.lostPetsButton.setBackgroundColor(Color.parseColor("#FFCF9E70"))
        }
        binding.lostPetsButton.setOnClickListener {
            fetchPublicationsByStatus(Publication.LOST)
            binding.lostPetsButton.setBackgroundColor(Color.GREEN)
            binding.adoptionPetsButton.setBackgroundColor(Color.parseColor("#FFCF9E70"))
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
        petsPublicationRecyclerView.adapter = this.petsPublicationAdapter

        binding.lostPetsButton.performClick()
    }

    private fun setFragment(fragment: Fragment) = requireActivity().supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        addToBackStack(null)
        commit()
    }

    private fun fetchPublicationsByStatus(status: String){
        petsPublicationAdapter.clearList(petsPublicationAdapter.itemCount)
        Firebase.firestore.collection("publications").get().addOnCompleteListener{ task->
            for(doc in task.result!!){
                val publication = doc.toObject(Publication::class.java)
                if(publication.status==status) {
                    petsPublicationAdapter.addPublication(publication)
                }
            }
        }
    }
}