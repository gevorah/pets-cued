package co.edu.icesi.dev.petscued.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.databinding.FragmentProfileBinding
import co.edu.icesi.dev.petscued.model.User
import co.edu.icesi.dev.petscued.utils.ImageUtils
import co.edu.icesi.dev.petscued.view.login.LoginActivity
import co.edu.icesi.dev.petscued.view.pets.LostPetFragment
import co.edu.icesi.dev.petscued.view.pets.PetAdoptionFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*

@Suppress("UNUSED_EXPRESSION")
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?{

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.publicationsBtn.setOnClickListener {
            val userPublicationsFragment = UserPublicationsFragment()
            setFragment(userPublicationsFragment)
        }
        binding.editProfileBtn.setOnClickListener {
            val editProfileFragment = EditProfileFragment()
            setFragment(editProfileFragment)
        }
        binding.questionsBtn.setOnClickListener {
            val helpFragment = HelpFragment()
            setFragment(helpFragment)
        }
        binding.logOutBtn.setOnClickListener{
            logout()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profilePic.setImageBitmap(ImageUtils.getRoundBitmap(binding.profilePic.drawable.toBitmap()))

        val user = loadUser()
        if(user == null || Firebase.auth.currentUser == null || Firebase.auth.currentUser?.isEmailVerified == false){
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
            return
        }else{
            this.user = user
        }
        Firebase.messaging.subscribeToTopic(user.id)
        binding.usernameTxt.text = user.name
        binding.emailTxt.text = user.email
        binding.phoneTxt.text = "3214567890"
    }

    private fun setFragment(fragment: Fragment) = requireActivity().supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        addToBackStack(null)
        commit()
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

    private fun logout(){
        activity?.finish()
        val user = loadUser()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        user?.let { it1 -> Firebase.messaging.unsubscribeFromTopic(it1.id) }
        val sp = requireActivity().getSharedPreferences("pets-cued", AppCompatActivity.MODE_PRIVATE)
        sp.edit().clear().apply()
        Firebase.auth.signOut()
    }
}