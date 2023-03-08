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
import com.example.phoneauthenticatin.databinding.VerifyEmailLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class VerifyEmailFragment:Fragment() {
    private lateinit var VerifyEmail: View
    lateinit var auth: FirebaseAuth
    private  lateinit var binding: VerifyEmailLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.verify_email_layout,container, false);
        VerifyEmail=binding.root
        auth= FirebaseAuth.getInstance()



        return VerifyEmail
    }
}