package com.example.phoneauthenticatin

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.phoneauthenticatin.databinding.ActivityOptViewBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OptView : AppCompatActivity() {
    private lateinit var binding:ActivityOptViewBinding
     lateinit var phonenumber:String
     lateinit var id:String
     lateinit var token: PhoneAuthProvider.ForceResendingToken
     lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_opt_view)
        setContentView(binding.root)
        supportActionBar?.hide()
        auth= FirebaseAuth.getInstance()

        val otp=binding.verifyopt
        val verify=binding.button2
        id=intent.getStringExtra("otp").toString()
        token=intent.getParcelableExtra("token")!!



        verify.setOnClickListener {
            phonenumber=otp.text.toString()

           if( phonenumber.isNotEmpty()){
               if( phonenumber.length==6){
                   binding.progressBar2.visibility= View.VISIBLE
                   val credential = PhoneAuthProvider.getCredential(id, phonenumber)
                   signInWithPhoneAuthCredential(credential)
               }else{
                   Toast.makeText(applicationContext,"wrong otp",Toast.LENGTH_SHORT).show()
               }

           }else{

               Toast.makeText(applicationContext,"Enter otp",Toast.LENGTH_SHORT).show()
           }
        }

    }

   fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    binding.progressBar2.visibility= View.INVISIBLE

                    Toast.makeText(this,
                        "signIn With phone success", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,HomeView::class.java)
                    startActivity(intent)
                } else {
                    // Sign in failed, display a message and update the UI
                    binding.progressBar2.visibility= View.INVISIBLE
                    Toast.makeText(this,
                        "You Enter Wrong OTP", Toast.LENGTH_SHORT).show()

                }
            }
    }

}