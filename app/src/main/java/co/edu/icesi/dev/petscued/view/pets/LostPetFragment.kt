package co.edu.icesi.dev.petscued.view.pets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentLostPetBinding
import co.edu.icesi.dev.petscued.model.Publication
import co.edu.icesi.dev.petscued.view.profile.UserPublicationsFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.UUID

class LostPetFragment : Fragment() {

    private lateinit var binding: FragmentLostPetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLostPetBinding.inflate(inflater, container, false)
        binding.lostPetBttn.setOnClickListener {
            publish()
            val userPublicationsFragment = UserPublicationsFragment()
            setFragment(userPublicationsFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setFragment(fragment: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            addToBackStack(null)
            commit()
        }

    private fun publish() {
        val image = binding.photoImg.toString()
        val name = binding.editTextTextPersonName.text.toString()
        val breed = binding.editTextTextPersonName2.text.toString()
        val sex = sexRadioGroup(binding.radioGroup.checkedRadioButtonId) // OK
        val owner = binding.editTextTextPersonName4.text.toString()
        val type = typeRadioGroup(binding.radioGroup.checkedRadioButtonId) // OK
        val status = Publication.LOST // OK
        val location = binding.editTextTextPersonName7.text.toString()
        val age = binding.editTextTextPersonName3.text.toString()
        val color = binding.editTextTextPersonName3.text.toString()
        val description = binding.editTextTextMultiLine.text.toString() // OK
        val contactInformation = binding.editTextTextPersonName.text.toString()
        val vaccinated = binding.editTextTextPersonName.text.toString()

        val publication = Publication(
            UUID.randomUUID().toString(),
            image,
            name,
            breed,
            sex,
            owner,
            type,
            status,
            location,
            age,
            color,
            description,
            contactInformation,
            vaccinated
        )

        Firebase.firestore.collection("publications").document(publication.id).set(publication)
    }

    fun typeRadioGroup(checkedId: Int): String {
        when(checkedId) {
            binding.radioButton.id -> {
                return binding.radioButton.text.toString()
            }
            binding.radioButton2.id -> {
                return binding.radioButton2.text.toString()
            }
            binding.radioButton3.id -> {
                return binding.radioButton3.text.toString()
            }
            binding.radioButton4.id -> {
                return binding.radioButton4.text.toString()
            }
            binding.radioButton5.id -> {
                return binding.radioButton5.text.toString()
            }
        }
        return ""
    }

    fun sexRadioGroup(checkedId: Int): String {
        when(checkedId) {
            binding.radioButton10.id -> {
                return binding.radioButton10.text.toString()
            }
            binding.radioButton11.id -> {
                return binding.radioButton11.text.toString()
            }
        }
        return ""
    }
}
