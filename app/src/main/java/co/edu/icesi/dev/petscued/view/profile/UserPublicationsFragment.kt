package co.edu.icesi.dev.petscued.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.icesi.dev.petscued.databinding.FragmentUserPublicationsBinding
import co.edu.icesi.dev.petscued.model.Publication
import kotlinx.android.synthetic.main.fragment_user_publications.*
import java.util.*
import kotlin.collections.ArrayList

class UserPublicationsFragment : Fragment() {

    private lateinit var binding: FragmentUserPublicationsBinding
    private lateinit var publicationLayoutManager: LinearLayoutManager
    private lateinit var userPublicationAdapter: UserPublicationAdapter
    private lateinit var publicationList: ArrayList<Publication>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPublicationsBinding.inflate(inflater, container, false)

        binding.lostPublicationButton.setOnClickListener {
            filterPublicationListByStatus(Publication.LOST)
        }
        binding.adoptionPublicationButton.setOnClickListener {
            filterPublicationListByStatus(Publication.ADOPTION)
        }
        binding.addFloatingActionButton.setOnClickListener(::addHardcodedElements)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        publicationLayoutManager = LinearLayoutManager(context)
        userPublicationRecyclerView.layoutManager = publicationLayoutManager
        userPublicationRecyclerView.setHasFixedSize(true)

        userPublicationAdapter = UserPublicationAdapter()
        userPublicationRecyclerView.adapter = userPublicationAdapter
    }

    private fun filterPublicationListByStatus(status: String){
        val filteredPublicationList: List<Publication> = publicationList.filter {
                publication -> publication.status.equals(status, ignoreCase = true)
        }
        userPublicationAdapter.setPublicationList(filteredPublicationList as ArrayList<Publication>)
    }

    private fun addHardcodedElements(view: View) {
        this.publicationList = ArrayList()
        publicationList.add(Publication(
            UUID.randomUUID().toString().toString(),
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
        publicationList.add(Publication(
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
        publicationList.add(Publication(
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
        userPublicationAdapter.setPublicationList(publicationList)
    }
}