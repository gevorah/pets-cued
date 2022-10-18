package co.edu.icesi.dev.petscued

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HelpActivity : AppCompatActivity() {

    val questioList= ArrayList<Questions>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        initData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val questionAdapter = QuestionAdapter(questioList)
       // recycler.adapter = questionAdapter
       // recycler.setHasFixedSize(true)
    }

    private fun initData() {
        questioList.add(Questions("¿Como publicar una mascota perdida?",
            "En la pantalla de inicio oprimir el boton publicar mascosta perdida"))
        questioList.add(Questions("¿Como publicar una mascota en adopcion?",
                "En la pantalla de inicio oprimir el boton publicar mascosta perdida"))
        questioList.add(Questions("¿Como adoptar una mascota?",
                "oprimir sobre la publicacion que te interesa y se desplega la informacion de contacto"))

    }
}