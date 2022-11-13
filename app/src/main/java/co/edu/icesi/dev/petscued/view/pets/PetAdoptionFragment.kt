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
        binding = FragmentPetAdoptionBinding.inflate(inflater, container, false)

        binding.adoptionPetBttn.setOnClickListener {
            val petsFragment = PetsFragment()
            setFragment(petsFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setFragment(fragment: Fragment) = requireActivity().supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        addToBackStack(null)
        commit()
    }
}