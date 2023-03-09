package com.example.phoneauthenticatin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.phoneauthenticatin.databinding.EmilLinkLayoutBinding

class SendEmailLinkView : AppCompatActivity() {
    private lateinit var binding:EmilLinkLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.emil_link_layout)
        setContentView(binding.root)

    }
}