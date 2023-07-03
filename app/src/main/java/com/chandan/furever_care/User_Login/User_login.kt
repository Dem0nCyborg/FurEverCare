package com.chandan.furever_care.User_Login

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.User.MainActivity
import com.chandan.furever_care.User.Shop
import com.chandan.furever_care.databinding.ActivityUserLoginBinding

class User_login : AppCompatActivity() {

    private lateinit var binding: ActivityUserLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uRegBtn.setOnClickListener {
            startActivity(Intent(this,User_Reg::class.java))
            Toast.makeText(this,"Get yourself registered..!",Toast.LENGTH_SHORT).show()
        }

        binding.uLgnBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }

    }
}