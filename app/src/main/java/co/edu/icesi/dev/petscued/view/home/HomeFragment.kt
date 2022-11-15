package co.edu.icesi.dev.petscued.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentHomeBinding
import co.edu.icesi.dev.petscued.model.Publication
import co.edu.icesi.dev.petscued.view.pets.LostPetFragment
import co.edu.icesi.dev.petscued.view.pets.PetAdoptionFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var publicationLayoutManager: GridLayoutManager
    private lateinit var homePublicationAdapter: HomePublicationAdapter
    private lateinit var publicationList: ArrayList<Publication>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lostImageButton.setOnClickListener {
            val lostPetFragment = LostPetFragment()
            setFragment(lostPetFragment)
        }
        binding.adoptionImageButton.setOnClickListener {
            val petAdoptionFragment = PetAdoptionFragment()
            setFragment(petAdoptionFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.publicationLayoutManager = GridLayoutManager(context, 2)
        homePublicationRecyclerView.layoutManager = publicationLayoutManager
        homePublicationRecyclerView.setHasFixedSize(true)

        homePublicationAdapter = HomePublicationAdapter()
        homePublicationRecyclerView.adapter = homePublicationAdapter

        addHardcodedElements()
    }

    private fun setFragment(fragment: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            addToBackStack(null)
            commit()
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
        homePublicationAdapter.setPublicationList(publicationList)
    }
}