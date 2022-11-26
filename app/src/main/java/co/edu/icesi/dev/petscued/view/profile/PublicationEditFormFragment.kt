package co.edu.icesi.dev.petscued.view.profile

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentPublicationFormBinding
import co.edu.icesi.dev.petscued.model.Publication
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*

class PublicationEditFormFragment(private var publication: Publication) : Fragment() {

    private lateinit var binding: FragmentPublicationFormBinding
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPublicationFormBinding.inflate(inflater, container, false)

        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

        binding.imageButtonPet.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }
        binding.lostPetBttn.setOnClickListener {
            if(update()){
                val userPublicationsFragment = UserPublicationsFragment()
                setFragment(userPublicationsFragment)
            }
        }
        binding.backLostPetButton.setOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Firebase.storage.reference.child("publications").child(publication.image).downloadUrl.addOnSuccessListener {
            Glide.with(binding.imageButtonPet).load(it).into(binding.imageButtonPet)
        }
        binding.editTextPetName.setText(publication.name)
        binding.editTextPetBreed.setText(publication.breed)
        binding.radioGroupType.check(getTypeRadioButtonIdFromValue(publication.type))
        binding.editTextPetOwner.setText(publication.owner)
        binding.radioGroupSex.check(getSexRadioButtonIdFromValue(publication.sex))
        binding.editTextAddress.setText(publication.location)
        binding.editTextPetAge.setText(publication.age)
        binding.editTextPetColor.setText(publication.color)
        binding.editTextMultiLineDescription.setText(publication.description)
        binding.editTextPhoneNumber.setText(publication.contactInformation)
    }

    private fun getTypeRadioButtonIdFromValue(value: String): Int {
        when(value) {
            "Perro" -> {
                return binding.radioButton.id
            }
            "Gato" -> {
                return binding.radioButton2.id
            }
            "Cerdo" -> {
                return binding.radioButton3.id
            }
            "Conejo" -> {
                return binding.radioButton4.id
            }
            "Otro" -> {
                return binding.radioButton5.id
            }
        }
        return -1
    }

    private fun getSexRadioButtonIdFromValue(value: String): Int {
        when(value) {
            Publication.MALE -> {
                return binding.radioButton10.id
            }
            Publication.FEMALE -> {
                return binding.radioButton11.id
            }
        }
        return -1
    }

    private fun setFragment(fragment: Fragment) = requireActivity().supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        addToBackStack(null)
        commit()
    }

    private fun onGalleryResult(result: ActivityResult) {
        if(result.resultCode == RESULT_OK) {
            uri = result.data?.data
            uri?.let {
                binding.imageButtonPet.setImageURI(uri)
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun update(): Boolean {
        if(!checkIfNotBlankOrEmpty(binding.editTextPetName.text.toString()))
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
        else if(!checkIfNotBlankOrEmpty(binding.editTextPhoneNumber.text.toString()))
            return false
        val image : String
            if(uri==null ){
            image = publication.image
        }else{
            Firebase.storage.reference.child("publications").child(publication.image).delete()
            image = UUID.randomUUID().toString()
            Firebase.storage.reference.child("publications").child(image).putFile(uri!!)
        }
        val name = binding.editTextPetName.text.toString()
        val breed = binding.editTextPetBreed.text.toString()
        val sex = sexRadioGroup(binding.radioGroupSex.checkedRadioButtonId) // OK
        val owner = binding.editTextPetOwner.text.toString()
        val type = typeRadioGroup(binding.radioGroupType.checkedRadioButtonId) // OK
        val status = publication.status // OK
        val location = binding.editTextAddress.text.toString()
        val age = binding.editTextPetAge.text.toString()
        val color = binding.editTextPetColor.text.toString()
        val description = binding.editTextMultiLineDescription.text.toString()
        val contactInformation = binding.editTextPhoneNumber.text.toString()

        this.publication = Publication(
            publication.id,
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
            Firebase.auth.currentUser!!.uid
        )
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