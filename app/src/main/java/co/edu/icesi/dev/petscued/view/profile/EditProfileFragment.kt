package co.edu.icesi.dev.petscued.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import co.edu.icesi.dev.petscued.databinding.FragmentEditProfileBinding
import co.edu.icesi.dev.petscued.databinding.FragmentProfileBinding
import co.edu.icesi.dev.petscued.model.User
import co.edu.icesi.dev.petscued.view.MainActivity
import com.google.gson.Gson

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        binding.saveBtn.setOnClickListener(::changePassword)
        //android:afterTextChanged="@{(edtitable)->LoginVM.afterUserNameChange(edtitable)}"
        //public void afterUserNameChange(CharSequence s)
        //{ Log.i("truc", s.toString()) }
        return binding.root
    }

    private fun changePassword(view: View){
        val newPassword = binding.editPassword1Txt.text.toString()

        if(newPassword.isEmpty()){
            binding.editPassword1Txt.error = "El campo no puede estar vacío"
            return
        }else binding.editPassword1Txt.error = null

        if(newPassword.length < 6){
            binding.editPassword1Txt.error = "La contraseña está muy corta, mínimo 6 caracteres"
            return
        }else binding.editPassword1Txt.error = null

        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Cambio de contraseña")
            .setMessage("¿Desea cambiar la contraseña?")
            .setPositiveButton("Si"){ dialog, _ ->
                Firebase.auth.currentUser?.updatePassword(newPassword)?.addOnSuccessListener {
                    Toast.makeText(context, "Contraseña cambiada!", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }?.addOnFailureListener {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }
        builder.show()
    }
}