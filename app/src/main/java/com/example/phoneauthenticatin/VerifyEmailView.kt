package com.example.phoneauthenticatin

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.phoneauthenticatin.databinding.VerifyEmailLayoutBinding
import com.example.phoneauthenticatin.utils.MainUtils
import com.google.firebase.FirebaseException
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings


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

        binding.verifyEmailLink.setOnClickListener {


            val emailID = binding.emailLink.getText().toString()
            binding.progressBar3.visibility = View.VISIBLE




                if (emailID.isNotEmpty()) {
                    val actionCodeSettings = actionCodeSettings {
                        // URL you want to redirect back to. The domain (www.example.com) for this
                        // URL must be whitelisted in the Firebase Console.
                        url = ("https://phone-auth.com")
                        // This must be true
                        handleCodeInApp = true
                        setAndroidPackageName(
                            BuildConfig.APPLICATION_ID,
                            true, /* installIfNotAvailable */
                            "12" )/* minimumVersion */

                    }
                    try{
                        auth.sendSignInLinkToEmail(emailID, actionCodeSettings)
                        .addOnCompleteListener { task ->
                            Log.d(TAG, "onComplete: ")
                            if (task.isSuccessful) {

                                khan.showToast(this, "please check your Email")
                                binding.progressBar3.visibility = View.INVISIBLE

                            } else {

                                println("userEmail$emailID ID and actioncode+> $actionCodeSettings")
                                khan.showToast(this, "Error")
                                binding.progressBar3.visibility = View.INVISIBLE
                            }
                        }

                    } catch (e: FirebaseException) {
                        // Handle errors


                        println("Hello firbase$e.toString()")
                    }
                } else {
                    binding.progressBar3.visibility = View.INVISIBLE
                    khan.showToast(this, "Enter Email")
                }


        }


    }

}