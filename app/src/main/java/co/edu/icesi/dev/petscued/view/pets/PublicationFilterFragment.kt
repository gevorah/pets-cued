package co.edu.icesi.dev.petscued.view.pets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentPublicationFilterBinding

class PublicationFilterFragment : Fragment() {

    private lateinit var binding: FragmentPublicationFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPublicationFilterBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
        binding.cleanButton.setOnClickListener {
            binding.editTextType.text.clear()
            binding.editTextColor.text.clear()
            binding.editTextBreed.text.clear()
            binding.editTextLocation.text.clear()
            binding.editTextAge.text.clear()
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