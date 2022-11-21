package co.edu.icesi.dev.petscued.view.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.icesi.dev.petscued.databinding.FragmentUserPublicationsBinding
import co.edu.icesi.dev.petscued.model.Publication
import co.edu.icesi.dev.petscued.model.User
import co.edu.icesi.dev.petscued.view.login.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_user_publications.*
import java.util.*


class UserPublicationsFragment() : Fragment() {

    private lateinit var user: User
    private lateinit var binding: FragmentUserPublicationsBinding
    private lateinit var publicationLayoutManager: LinearLayoutManager
    private lateinit var userPublicationAdapter: UserPublicationAdapter
    private lateinit var userPublicationList: ArrayList<Publication>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentUserPublicationsBinding.inflate(inflater, container, false)
        binding.lostPublicationButton.setOnClickListener {
            binding.lostPublicationButton.setBackgroundColor(Color.GREEN)
            binding.adoptionPublicationButton.setBackgroundColor(Color.parseColor("#FFCF9E70"))
            filterPublicationListByStatus(Publication.LOST)
        }
        binding.adoptionPublicationButton.setOnClickListener {
            binding.adoptionPublicationButton.setBackgroundColor(Color.GREEN)
            binding.lostPublicationButton.setBackgroundColor(Color.parseColor("#FFCF9E70"))
            filterPublicationListByStatus(Publication.ADOPTION)
        }
        binding.addFloatingActionButton.setOnClickListener {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        verifyIfUserIsLoggedin()
        publicationLayoutManager = LinearLayoutManager(context)
        userPublicationRecyclerView.layoutManager = publicationLayoutManager
        userPublicationRecyclerView.setHasFixedSize(true)
        userPublicationAdapter = UserPublicationAdapter()
        userPublicationRecyclerView.adapter = userPublicationAdapter
        userPublicationList = ArrayList()
        loadUserPublicationsFromFirebase()
        binding.lostPublicationButton.performClick()
    }

    private fun filterPublicationListByStatus(status: String){
        val filteredPublicationList: List<Publication> = userPublicationList.filter {
                publication -> publication.status.equals(status, ignoreCase = true)
        }
        userPublicationAdapter.setPublicationList(filteredPublicationList as ArrayList<Publication>)
    }

    private fun loadUserPublicationsFromFirebase(){
        Firebase.firestore.collection("publications")
            .whereEqualTo("userId", user.id).get().addOnCompleteListener{ task->
            for(doc in task.result!!){
                val publication = doc.toObject(Publication::class.java)
                userPublicationList.add(publication)
                Log.e(">>", publication.name)
            }
        }
        userPublicationAdapter.setPublicationList(userPublicationList)
    }

    private fun verifyIfUserIsLoggedin(){
        val user = loadUser()
        if(user == null || Firebase.auth.currentUser == null || Firebase.auth.currentUser?.isEmailVerified == false){
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
            return
        }else{
            this.user = user
        }
    }

    private fun loadUser(): User? {
        val sp = requireActivity().getSharedPreferences("pets-cued", AppCompatActivity.MODE_PRIVATE)
        val json = sp.getString("user", "NO_USER")
        return if(json == "NO_USER"){
            null
        }else{
            Gson().fromJson(json, User::class.java)
        }
    }

    private fun addHardcodedElements() {
        userPublicationList.add(Publication(
            UUID.randomUUID().toString(),
            "path",
            "Laila",
            "Mestiza",
            "Male",
            "José Castro",
            "dog",
            "Perdido",
            "Cali, Santa Mónica",
            "7 años",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "Sí"))
        userPublicationList.add(Publication(
            UUID.randomUUID().toString(),
            "path",
            "Cat",
            "Mestiza",
            "Male",
            "José Castro",
            "dog",
            "Adopción",
            "Cali, Santa Mónica",
            "7 años",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "Sí"))
        userPublicationList.add(Publication(
            UUID.randomUUID().toString(),
            "path",
            "Duck",
            "Mestiza",
            "Male",
            "José Castro",
            "dog",
            "Adopción",
            "Cali, Santa Mónica",
            "7 años",
            "cafe",
            "sin detalles adiciones.",
            "3152942393",
            "Sí"))
    }
}