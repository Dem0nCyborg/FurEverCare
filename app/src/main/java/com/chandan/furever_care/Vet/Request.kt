package com.chandan.furever_care.Vet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.R
import com.chandan.furever_care.User.Care
import com.chandan.furever_care.User.MainActivity
import com.chandan.furever_care.User.Profile
import com.chandan.furever_care.User.Shop
import com.chandan.furever_care.databinding.ActivityRequestBinding

class Request : AppCompatActivity() {

    private lateinit var binding: ActivityRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btmNavVet.selectedItemId = R.id.Request

        binding.btmNavVet.setOnItemSelectedListener { item ->
            when (item.itemId)
            {
                R.id.Request -> {
                    startActivity(Intent(applicationContext,Request::class.java))
                    overridePendingTransition(0,0)
                    true
                }

                R.id.Accepted -> {
                    startActivity(Intent(applicationContext, Accepted::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }

                R.id.Vprofile -> {
                    startActivity(Intent(applicationContext, VetProfile::class.java))
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