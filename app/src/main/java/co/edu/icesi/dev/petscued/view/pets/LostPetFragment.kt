package co.edu.icesi.dev.petscued.view.pets

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentLostPetBinding
import co.edu.icesi.dev.petscued.model.Publication
import co.edu.icesi.dev.petscued.view.profile.UserPublicationsFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*

class LostPetFragment : Fragment() {

    private lateinit var binding: FragmentLostPetBinding
    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLostPetBinding.inflate(inflater, container, false)

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

        binding.imageButtonPet.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

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

    private fun onGalleryResult(result: ActivityResult) {
        if(result.resultCode == Activity.RESULT_OK) {
            uri = result.data?.data
            uri?.let {
                binding.imageButtonPet.setImageURI(uri)
            }
        }
    }

    private fun publish() {
        val image = uri!!.toString() // OK
        val name = binding.editTextPetName.text.toString()
        val breed = binding.editTextPetBreed.text.toString()
        val sex = sexRadioGroup(binding.radioGroupSex.checkedRadioButtonId) // OK
        val owner = binding.editTextPetOwner.text.toString()
        val type = typeRadioGroup(binding.radioGroupType.checkedRadioButtonId) // OK
        val status = Publication.LOST // OK
        val location = binding.editTextAddress.text.toString()
        val age = binding.editTextPetAge.text.toString()
        val color = binding.editTextPetColor.text.toString()
        val description = binding.editTextMultiLineDescription.text.toString() // OK
        val contactInformation = binding.editTextPhoneNumber.text.toString()

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
            null
        )

        Firebase.storage.reference.child("publications").child(UUID.randomUUID().toString()).putFile(uri!!)
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