package co.edu.icesi.dev.petscued.view.pets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentPetsBinding
import co.edu.icesi.dev.petscued.model.Publication
import kotlinx.android.synthetic.main.fragment_pets.*
import java.util.*
import kotlin.collections.ArrayList

class PetsFragment : Fragment() {

    private lateinit var binding: FragmentPetsBinding
    private lateinit var publicationLayoutManager: GridLayoutManager
    private lateinit var petsPublicationAdapter: PetsPublicationAdapter
    private lateinit var publicationList: ArrayList<Publication>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetsBinding.inflate(inflater, container, false)

        binding.adoptionPetsButton.setOnClickListener {
            filterPublicationListByStatus(Publication.LOST)
        }
        binding.lostPetsButton.setOnClickListener {
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

        addHardcodedElements()
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

    private fun addHardcodedElements() {
        this.publicationList = ArrayList()
        publicationList.add(
            Publication(
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
                "Sí"
            )
        )
        publicationList.add(
            Publication(
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
                "Sí"
            )
        )
        publicationList.add(
            Publication(
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
                "Sí"
            )
        )
        petsPublicationAdapter.setPublicationList(publicationList)
    }
}