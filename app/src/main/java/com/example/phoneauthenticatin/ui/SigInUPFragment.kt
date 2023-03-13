package com.example.phoneauthenticatin.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.phoneauthenticatin.LoginView
import com.example.phoneauthenticatin.MainActivity
import com.example.phoneauthenticatin.R
import com.example.phoneauthenticatin.databinding.SignUpViewBinding
import com.example.phoneauthenticatin.utils.MainUtils
import com.google.firebase.auth.FirebaseAuth

class SigInUPFragment:Fragment() {
  lateinit var SigIn:View
  private lateinit var binding:SignUpViewBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_up_view ,container, false);
        SigIn=binding.root
        auth= FirebaseAuth.getInstance()
        val showToast=MainUtils()


        binding.Back.setOnClickListener{
                (requireActivity() as LoginView).onBackPressed()
        }
        val userEmail=binding.userName
        val UserPassword=binding.userPassword

        binding.sigIN.setOnClickListener{
            val email=userEmail.text.toString()
            val password=UserPassword.text.toString()



            if(email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                showToast.showToast(SigIn.context,"email is validate")

            }else{
                showToast.showToast(SigIn.context,"Enter correct email")
            }
//          if(email.isNotEmpty()&&password.isNotEmpty()){
////              auth.createUserWithEmailAndPassword(email, password)
////                  .addOnCompleteListener { task ->
////                      if (task.isSuccessful) {
////                          auth.currentUser?.sendEmailVerification()?.addOnSuccessListener{
////
////                              showToast.showToast(SigIn.context,msg = "Please Verify Email")
////
////                          }?.addOnFailureListener(){
////                              Toast.makeText(SigIn.context, it.toString(),
////                                  Toast.LENGTH_SHORT).show()
////                          }
//////                        Toast.makeText(SigIn.context, "Authentication success.",
//////                            Toast.LENGTH_SHORT).show()
//////                        Log.d(ContentValues.TAG, "signInWithEmail:success")
////
////                      } else {
////                          // If sign in fails, display a message to the user.
////                          Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
////                          showToast.showToast(SigIn.context,msg = "Authentication failed.")
////
////
////                      }
////                  }
//
//          }else{
//             showToast.showToast(SigIn.context,msg = "Enter Email and Password")
//          }
        }


        return SigIn
    }
}