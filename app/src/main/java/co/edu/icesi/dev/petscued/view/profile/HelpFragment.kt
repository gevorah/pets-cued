package co.edu.icesi.dev.petscued.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentHelpBinding
import co.edu.icesi.dev.petscued.model.Questions
import kotlinx.android.synthetic.main.fragment_help.*

class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding
    val questioList = ArrayList<Questions>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        setRecyclerView()
        val binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setRecyclerView() {
        val questionAdapter = QuestionsAdapter(questioList)
        recycler.adapter = questionAdapter
        recycler.setHasFixedSize(true)
    }

    private fun initData() {
        questioList.add(
            Questions(
                "¿Como publicar una mascota perdida?",
                "En la pantalla de inicio oprimir el boton publicar mascosta perdida"
            )
        )
        questioList.add(
            Questions(
                "¿Como publicar una mascota en adopcion?",
                "En la pantalla de inicio oprimir el boton publicar mascosta perdida"
            )
        )
        questioList.add(
            Questions(
                "¿Como adoptar una mascota?",
                "oprimir sobre la publicacion que te interesa y se desplega la informacion de contacto"
            )
        )

    }
}