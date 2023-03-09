package com.example.phoneauthenticatin

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import com.example.phoneauthenticatin.databinding.VerifyEmailLayoutBinding
import com.example.phoneauthenticatin.utils.MainUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import java.util.*


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


           val emailID=binding.emailLink.text.toString()
            binding.progressBar3.visibility= View.VISIBLE

            val actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://phone-auth-f0bfe.web.app")
                .setHandleCodeInApp(true)
                .setAndroidPackageName(
                    "com.example.phoneauthenticatin",
                    false, /* installIfNotAvailable */
                    "12" /* minimumVersion */)
                .build()
          if(emailID.isNotEmpty()){
              auth.sendSignInLinkToEmail(emailID, actionCodeSettings)
                  .addOnCompleteListener(OnCompleteListener<Void?> { task ->
                      Log.d(TAG, "onComplete: ")
                      if (task.isSuccessful) {

                          khan.showToast(this,"please check your Email")
                          binding.progressBar3.visibility= View.INVISIBLE

                      } else {

                          println("userEmail$emailID ID and actioncode$actionCodeSettings")
                          khan.showToast(this,"Error")
                          binding.progressBar3.visibility= View.INVISIBLE
                      }
                  })
          }else{
              binding.progressBar3.visibility= View.INVISIBLE
              khan.showToast(this,"Enter Email")
          }

        }



    }
}