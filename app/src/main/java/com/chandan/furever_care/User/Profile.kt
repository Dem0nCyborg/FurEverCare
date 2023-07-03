package com.chandan.furever_care.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.R
import com.chandan.furever_care.User_Login.User_Reg
import com.chandan.furever_care.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btmNavBar.setSelectedItemId(R.id.profile)
        binding.btmNavBar.setOnItemSelectedListener { item ->
            when (item.itemId)
            {
                R.id.shop -> {
                    startActivity(Intent(applicationContext,Shop::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }
                R.id.care -> {
                    startActivity(Intent(applicationContext,Care::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }
                R.id.home -> {
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }
                else -> {
                    false
                }
            }

        }

    }
}