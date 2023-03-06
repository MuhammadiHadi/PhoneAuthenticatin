package com.example.phoneauthenticatin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.phoneauthenticatin.databinding.ActivityMainBinding
import com.example.phoneauthenticatin.ui.LoginFragment
import com.google.android.gms.common.util.DataUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(binding.root)
        supportActionBar?.hide()

         binding.button.setOnClickListener{
//
//        Toast.makeText(this,
//           "Utility Bill", Toast.LENGTH_SHORT).show()
        replaceFragment(LoginFragment())
   }



    }
    fun replaceFragment(Fragment: Fragment){
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.homeLayout,Fragment)
        fragmentTransaction.addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0)
            super.onBackPressed()
        else
            supportFragmentManager.popBackStack()

    }
}