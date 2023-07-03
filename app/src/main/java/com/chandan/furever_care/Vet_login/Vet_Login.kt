package com.chandan.furever_care.Vet_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.Vet.Request
import com.chandan.furever_care.Vet.VetProfile
import com.chandan.furever_care.databinding.ActivityVetLoginBinding

class Vet_Login : AppCompatActivity() {

    private lateinit var binding: ActivityVetLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVetLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vRegBtn.setOnClickListener {
            startActivity(Intent(this,Vet_Reg::class.java))
        }

        binding.vLgnBtn.setOnClickListener {
            startActivity(Intent(this,Request::class.java))
            finish()
        }

    }
}