package com.example.phoneauthenticatin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class UserProfile : AppCompatActivity() {
    lateinit var ivImage: ImageView
    lateinit var tvName: TextView
    lateinit var btLogout: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        supportActionBar?.hide()

        ivImage = findViewById(R.id.iv_image)
        tvName = findViewById(R.id.tv_name)
        btLogout = findViewById(R.id.bt_logout)

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize firebase user
        val firebaseUser = firebaseAuth.currentUser

        // Check condition
        if (firebaseUser != null) {
            // When firebase user is not equal to null set image on image view
            Glide.with(this).load(firebaseUser.photoUrl).into(ivImage)
            // set name on text view
            tvName.text = firebaseUser.displayName
        }

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
        btLogout.setOnClickListener {
            // Sign out from google
            googleSignInClient.signOut().addOnCompleteListener { task ->
                // Check condition
                if (task.isSuccessful) {
                    // When task is successful sign out from firebase
                    firebaseAuth.signOut()
                    // Display Toast
                    Toast.makeText(applicationContext, "Logout successful", Toast.LENGTH_SHORT).show()
                    // Finish activity
                    finish()
                }
            }
        }
    }
}