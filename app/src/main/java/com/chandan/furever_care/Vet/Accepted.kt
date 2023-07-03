package com.chandan.furever_care.Vet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityAcceptedBinding

class Accepted : AppCompatActivity() {

    private lateinit var binding: ActivityAcceptedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcceptedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btmNavVet.setSelectedItemId(R.id.Accepted)
        binding.btmNavVet.setOnItemSelectedListener { item ->
            when (item.itemId)
            {
                R.id.Request -> {
                    startActivity(Intent(applicationContext, Request::class.java))
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