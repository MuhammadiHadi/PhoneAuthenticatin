package com.example.phoneauthenticatin

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.phoneauthenticatin.databinding.SigninLayoutBinding
import com.example.phoneauthenticatin.ui.ForgotPasswordFragment
import com.example.phoneauthenticatin.ui.LoginFragment
import com.example.phoneauthenticatin.ui.SigInUPFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginView : AppCompatActivity() {
    private lateinit var binding: SigninLayoutBinding
    lateinit var auth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.signin_layout)
        setContentView(binding.root)
        supportActionBar?.hide()
        auth=FirebaseAuth.getInstance()


        binding.backtosign.setOnClickListener{

            replaceFragment(SigInUPFragment())
        }

        binding.ForgotPassword.setOnClickListener{

            replaceFragment(ForgotPasswordFragment())

        }
        binding.phonenumber.setOnClickListener{
            replaceFragment(LoginFragment())
        }
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("333418934854-div9rj80blq7apnriiv3i8gv9tdab0qg.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        binding.googleLog.setOnClickListener{
            val intent: Intent = googleSignInClient.signInIntent
               startActivityForResult(intent,10)
        }
       val useremail=binding.PersonName
        val  userpassword=binding.password
        binding.sigIN.setOnClickListener{
            val email= useremail.text.toString()
            val password= userpassword.text.toString()
            if( email.isNotEmpty()&&password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener (this){ task ->
                        if (task.isSuccessful) {

                            val verifyEmail= auth.currentUser?.isEmailVerified
                            if(verifyEmail==true){
                                Toast.makeText(baseContext, "signInWithEmail success.",
                                    Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, UserProfile::class.java))
//                        Log.d(TAG, "signInWithEmail:success")
                            }else{
                                Toast.makeText(baseContext, "Please Verify Your Email",
                                    Toast.LENGTH_SHORT).show()
                            }
//                        Toast.makeText(baseContext, "signInWithEmail success.",
//                            Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this, UserProfile::class.java))
////                        Log.d(TAG, "signInWithEmail:success")

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, it.toString(),
                                Toast.LENGTH_SHORT).show()

                        }
                        println("usesrEmal$email")
                        println("usesrPassword$password")
                    }

            }else{
                Toast.makeText(this,"Enter Email and Password",Toast.LENGTH_SHORT).show()

            }

        }



    }



    fun replaceFragment(Fragment: Fragment){
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.loginview,Fragment)
        fragmentTransaction.addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0)
            super.onBackPressed()
        else
            supportFragmentManager.popBackStack()

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w(ContentValues.TAG, "Google sign in failed", e)
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