package com.chandan.furever_care.Vet_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityVetLoginBinding
import com.chandan.furever_care.databinding.ActivityVetRegBinding

class Vet_Reg : AppCompatActivity() {

    private lateinit var binding: ActivityVetRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVetRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vLgnBtn.setOnClickListener {
            startActivity(Intent(this,Vet_Login::class.java))
        }
    }
}