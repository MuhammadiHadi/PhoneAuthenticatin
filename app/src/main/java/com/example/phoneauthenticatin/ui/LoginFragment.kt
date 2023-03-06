package com.example.phoneauthenticatin.ui

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.phoneauthenticatin.OptView
import com.example.phoneauthenticatin.R
import com.example.phoneauthenticatin.databinding.LoginFragmentBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class LoginFragment :Fragment() {
    lateinit var login:View
    private lateinit var binding:LoginFragmentBinding
    lateinit var auth:FirebaseAuth
    lateinit var phone:String


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        login=binding.root

        val mobileNumber=binding.mobileNumber
        val Login_button=binding.loginButton
        val progressBar=binding.progressBar


        //body
        auth= FirebaseAuth.getInstance()
        Login_button.setOnClickListener{



            phone=mobileNumber.text.trim().toString()
            if(phone.isNotEmpty()){
                if(phone.length==13){
                        phone=phone
                    progressBar.visibility=View.VISIBLE
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(login.context as Activity)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                }else{
                    Toast.makeText(login.context,
                        "Wrong number", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(login.context,
                    "Enter number", Toast.LENGTH_SHORT).show()
            }


        }



        return login


    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Toast.makeText(login.context,
                        "signInWithCredential:success", Toast.LENGTH_SHORT).show()
                    val user = task.result?.user
                } else {
                    binding.progressBar.visibility= View.INVISIBLE
                    // Sign in failed, display a message and update the UI
                    Toast.makeText(login.context,
                        "signInWithCredential:failure", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        binding.progressBar.visibility= View.INVISIBLE
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
  val  callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            binding.progressBar.visibility= View.INVISIBLE
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            binding.progressBar.visibility= View.INVISIBLE
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }


        }

        override fun onCodeSent(

            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            binding.progressBar.visibility= View.INVISIBLE
            val intent= Intent(login.context,OptView::class.java)
            intent.putExtra("otp" ,verificationId)
            intent.putExtra("token",token)
            intent.putExtra("phone",phone)
            startActivity(intent)

        }
    }

}