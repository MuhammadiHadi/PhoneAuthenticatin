package com.example.phoneauthenticatin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.phoneauthenticatin.LoginView
import com.example.phoneauthenticatin.R
import com.example.phoneauthenticatin.databinding.ForgotPasswordLayoutBinding
import com.example.phoneauthenticatin.databinding.SigninLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment:Fragment() {
    private lateinit var Forgot:View
    lateinit var auth: FirebaseAuth
   private  lateinit var binding: ForgotPasswordLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.forgot_password_layout,container, false);
        Forgot=binding.root
        auth= FirebaseAuth.getInstance()

        binding.backButton.setOnClickListener{
            (requireActivity() as LoginView).onBackPressed()
        }
       val emailValue=binding.forgotPasswordEmail
        binding.forgotPassword.setOnClickListener{
            val email=emailValue.text.trim().toString()

            if(email.isNotEmpty()){


                auth.sendPasswordResetEmail(email).addOnSuccessListener {

                    Toast.makeText(Forgot.context, "Please Check Your Email", Toast.LENGTH_SHORT).show()
                }?.addOnFailureListener(){


                    Toast.makeText(Forgot.context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            } else{

                    Toast.makeText(Forgot.context, "Enter Email", Toast.LENGTH_SHORT).show()
                }


        }

        return Forgot
    }
}