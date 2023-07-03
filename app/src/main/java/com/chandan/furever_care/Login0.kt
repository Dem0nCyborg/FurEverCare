package com.chandan.furever_care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.chandan.furever_care.User_Login.User_login
import com.chandan.furever_care.Vet_login.Vet_Login
import com.chandan.furever_care.databinding.ActivityLogin0Binding

class Login0 : AppCompatActivity() {

    private lateinit var binding: ActivityLogin0Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin0Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOwn.setOnClickListener {
            val intent = Intent(this,User_login::class.java)
            startActivity(intent)
            Toast.makeText(this,"Owner is Clicked",Toast.LENGTH_SHORT).show()
        }

        binding.btnVet.setOnClickListener {
            val intent = Intent(this,Vet_Login::class.java)
            startActivity(intent)
            Toast.makeText(this,"Owner is Clicked",Toast.LENGTH_SHORT).show()
        }
    }
}