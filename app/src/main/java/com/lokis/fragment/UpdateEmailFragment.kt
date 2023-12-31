@file:Suppress("LABEL_NAME_CLASH", "DEPRECATION")

package com.lokis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lokis.R
import com.google.firebase.auth.FirebaseAuth

class UpdateEmailFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_email, container, false)
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val layoutPassword: LinearLayout = view.findViewById(R.id.layoutPassword)
        val layoutEmail: LinearLayout = view.findViewById(R.id.layoutEmail)
        val etPassword: EditText = view.findViewById(R.id.etPassword)
        val btnAuth: Button = view.findViewById(R.id.btnAuth)
        val btnUpdate: Button = view.findViewById(R.id.btnUpdate)
        val etEmail: EditText = view.findViewById(R.id.etEmail)

        layoutPassword.visibility = View.VISIBLE
        layoutEmail.visibility = View.GONE

        btnAuth.setOnClickListener {
            val password = etPassword.text.toString().trim()
            if (password.isEmpty()){
                etPassword.error = "Password Harus Diisi"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            user?.let{
                val userCredentials = EmailAuthProvider.getCredential(it.email!!, password)
                it.reauthenticate(userCredentials).addOnCompleteListener {task ->
                    when {
                        task.isSuccessful -> {
                            layoutPassword.visibility = View.GONE
                            layoutEmail.visibility = View.VISIBLE
                        }
                        task.exception is FirebaseAuthActionCodeException -> {
                            etPassword.error = "Password Salah"
                            etPassword.requestFocus()
                        }
                        else -> {
                            Toast.makeText(activity, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            btnUpdate.setOnClickListener {
                val email = etEmail.text.toString().trim()

                if (email.isEmpty()){
                    etEmail.error = "Email harus diisi!"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.error = "Email tidak valid!"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                user.let {
                    user?.updateEmail(email)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            findNavController().navigate(R.id.action_updateEmailFragment_to_profileFragment)
                        } else{
                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }*/
}