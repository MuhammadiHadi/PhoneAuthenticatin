package com.example.phoneauthenticatin

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.phoneauthenticatin.databinding.ActivityMainBinding
import com.example.phoneauthenticatin.ui.SigInUPFragment
import com.example.phoneauthenticatin.ui.VerifyEmailFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var auth:FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(binding.root)
        supportActionBar?.hide()

            auth=FirebaseAuth.getInstance()



        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("333418934854-div9rj80blq7apnriiv3i8gv9tdab0qg.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Initialize sign in client
       val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

//           binding.googleLogin.setOnClickListener {
//
//               val intent: Intent = googleSignInClient.signInIntent
//               startActivityForResult(intent,10)
//
//           }
//       auth = FirebaseAuth.getInstance()
//        // Initialize firebase user
//        val firebaseUser: FirebaseUser? =   auth .currentUser
//        // Check condition
//        if (firebaseUser != null) {
//            // When user already sign in redirect to profile activity
//            startActivity(
//                Intent(
//                    this@MainActivity,
//                    UserProfile::class.java
//                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            )
//        }

        // go to Loginview
        binding.button.setOnClickListener{
            val intent=Intent(this,LoginView::class.java)
            startActivity(intent)
        }

        binding.signUPbutton.setOnClickListener{
            startActivity(Intent(this,VerifyEmailView::class.java))
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener(this) { authResult ->
                startActivity(Intent(this, UserProfile::class.java))
            }
            .addOnFailureListener(this) { e ->
                Toast.makeText(
                    this, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }



}