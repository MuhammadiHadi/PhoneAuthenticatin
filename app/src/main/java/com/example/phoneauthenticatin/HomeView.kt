package com.example.phoneauthenticatin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.phoneauthenticatin.databinding.ActivityHomeViewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeView : AppCompatActivity() {
    private lateinit var  binding:ActivityHomeViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home_view)
        setContentView(binding.root)
        supportActionBar?.hide()


        val firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        val userphone = user!!.phoneNumber
        val useruid = user!!.uid

//        println("Meer Hadi khan$userKey")

        binding.phone.setText(userphone.toString())
        binding.uid.setText(useruid.toString())

//        binding.logout.setOnClickListener{
//            Firebase.auth.signOut()
////            val intent= Intent(this,OptView::class.java)
////            startActivity(intent)
//        }

    }
}