package com.example.phoneauthenticatin

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.phoneauthenticatin.databinding.VerifyEmailLayoutBinding
import com.example.phoneauthenticatin.ui.SendEmailLinkFragment
import com.example.phoneauthenticatin.utils.MainUtils
import com.google.firebase.auth.FirebaseAuth

class VerifyEmailView : AppCompatActivity() {
    private lateinit var binding:VerifyEmailLayoutBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.verify_email_layout)
        setContentView(binding.root)
        supportActionBar?.hide()
         auth= FirebaseAuth.getInstance()



        val khan=MainUtils()
        binding.verifyEmailLink.setOnClickListener{

//            val email=emailvale.text.trim().toString()
//            auth.signInWithEmailLink(email)?.addOnSuccessListener {
//                Toast.makeText(this,"Please Check Your Email",Toast.LENGTH_SHORT).show()
//            }?.addOnFailureListener(){
//                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()

        }



    }
    fun replaceFragment(Fragment: Fragment){
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.verifylayout,Fragment)
        fragmentTransaction.addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0)
            super.onBackPressed()
        else
            supportFragmentManager.popBackStack()

    }
}