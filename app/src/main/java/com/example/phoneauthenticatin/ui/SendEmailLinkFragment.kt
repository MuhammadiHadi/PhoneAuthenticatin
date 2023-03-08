package com.example.phoneauthenticatin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.phoneauthenticatin.R
import com.example.phoneauthenticatin.databinding.EmilLinkLayoutBinding
import com.example.phoneauthenticatin.databinding.VerifyEmailLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class SendEmailLinkFragment:Fragment() {
    private lateinit var SendEmail: View
    lateinit var auth: FirebaseAuth
    private  lateinit var binding: EmilLinkLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.emil_link_layout,container, false);
        SendEmail=binding.root
        auth= FirebaseAuth.getInstance()



        return SendEmail
    }
}