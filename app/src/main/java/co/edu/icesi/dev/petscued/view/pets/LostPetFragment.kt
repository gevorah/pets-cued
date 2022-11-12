package co.edu.icesi.dev.petscued.view.pets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.databinding.FragmentLostPetBinding

class LostPetFragment : Fragment() {

    private lateinit var binding: FragmentLostPetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLostPetBinding.inflate(inflater, container, false)
        binding.lostPetBttn.setOnClickListener {
            // pets publication fragment
        }
        return binding.root
    }
}
