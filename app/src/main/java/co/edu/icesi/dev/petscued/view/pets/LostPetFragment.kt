package co.edu.icesi.dev.petscued.view.pets

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentLostPetBinding
import co.edu.icesi.dev.petscued.model.Publication
import co.edu.icesi.dev.petscued.view.profile.UserPublicationsFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*

class LostPetFragment : Fragment() {

    private lateinit var binding: FragmentLostPetBinding
    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
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
        binding.backLostPetButton.setOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
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

    private fun publish(): Boolean {
        if(uri==null){
            Toast.makeText(this.context, "Debe subir una imagen de la mascota", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(!checkIfNotBlankOrEmpty(binding.editTextPetName.text.toString()))
            return false
        else if(!checkIfNotBlankOrEmpty(binding.editTextPetBreed.text.toString()))
            return false
        else if(!checkRadioButton(binding.radioGroupSex.checkedRadioButtonId))
            return false
        else if(!checkIfNotBlankOrEmpty(binding.editTextPetOwner.text.toString()))
            return false
        else if(!checkRadioButton(binding.radioGroupType.checkedRadioButtonId))
            return false
        else if(!checkIfNotBlankOrEmpty(binding.editTextAddress.text.toString()))
            return false
        else if(!checkIfNotBlankOrEmpty(binding.editTextPetAge.text.toString()))
            return false
        else if(!checkIfNotBlankOrEmpty(binding.editTextPetColor.text.toString()))
            return false
        else if(!checkIfNotBlankOrEmpty(binding.editTextMultiLineDescription.text.toString()))
            return false
        else if(!checkIfNotBlankOrEmpty(binding.editTextPhoneNumber.text.toString()))
            return false

        val image = UUID.randomUUID().toString()  // OK
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
            null,
            Firebase.auth.currentUser!!.uid
        )
        Firebase.storage.reference.child("publications").child(image).putFile(uri!!)
        Firebase.firestore.collection("publications").document(publication.id).set(publication)
        return true
    }

    private fun checkIfNotBlankOrEmpty(field: String): Boolean {
        if(field.isNotBlank() && field.isNotEmpty()){
            return true
        }
        Toast.makeText(this.context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun checkRadioButton(id: Int): Boolean {
        if(id!=-1){
            return true
        }
        Toast.makeText(this.context, "Escoja una opción", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun typeRadioGroup(checkedId: Int): String {
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

    private fun sexRadioGroup(checkedId: Int): String {
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
