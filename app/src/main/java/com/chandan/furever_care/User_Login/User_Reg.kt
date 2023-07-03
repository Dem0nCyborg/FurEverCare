package com.chandan.furever_care.User_Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityUserRegBinding

class User_Reg : AppCompatActivity() {

    private lateinit var binding: ActivityUserRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uLgnBtn.setOnClickListener {
            startActivity(Intent(this,User_login::class.java))
            Toast.makeText(this,"Continue to login",Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}