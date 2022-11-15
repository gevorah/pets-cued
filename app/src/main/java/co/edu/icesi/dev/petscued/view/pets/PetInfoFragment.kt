package co.edu.icesi.dev.petscued.view.pets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.icesi.dev.petscued.databinding.FragmentPetInfoBinding

class PetInfoFragment : Fragment() {

    private lateinit var binding: FragmentPetInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                                ): View? {
        binding = FragmentPetInfoBinding.inflate(inflater, container, false)

        binding.shareBtn.setOnClickListener {
            // do something
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}