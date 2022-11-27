package co.edu.icesi.dev.petscued.view.pets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.databinding.FragmentPetInfoBinding
import co.edu.icesi.dev.petscued.model.Publication
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class PetInfoFragment(private val publication : Publication) : Fragment() {

    private lateinit var binding: FragmentPetInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                                ): View {
        binding = FragmentPetInfoBinding.inflate(inflater, container, false)

        binding.backPetInfoButton.setOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Firebase.storage.reference.child("publications").child(publication.image).downloadUrl.addOnSuccessListener {
            Glide.with(binding.imageViewPet).load(it).into(binding.imageViewPet)
        }
        binding.textViewName.text = publication.name
        binding.textViewLocation.text = publication.location
        binding.textViewOwner.text = publication.owner
        binding.textViewSex.text = publication.sex
        binding.textViewAge.text = publication.age
        binding.textViewBreed.text = publication.breed
        binding.editTextMultiLineDetails.setText(publication.description)
        binding.textViewContactInfo.text = publication.contactInformation
    }
}