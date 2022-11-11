package co.edu.icesi.dev.petscued.view.pets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentPetAdoptionBinding
import co.edu.icesi.dev.petscued.databinding.FragmentProfileBinding


class PetAdoptionFragment : Fragment() {

    private lateinit var binding: FragmentPetAdoptionBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentPetAdoptionBinding.inflate(inflater, container, false)
        binding.adoptionPetBttn.setOnClickListener(::changeScreen)
        return binding.root

        return inflater.inflate(R.layout.fragment_pet_adoption, container, false)
    }

    private fun changeScreen(view: View?) {

    }


}