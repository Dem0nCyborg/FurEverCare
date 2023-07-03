package com.chandan.furever_care.Vet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityVetProfileBinding

class VetProfile : AppCompatActivity() {

    private lateinit var binding: ActivityVetProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVetProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btmNavVet.setSelectedItemId(R.id.Vprofile)
        binding.btmNavVet.setOnItemSelectedListener { item ->
            when (item.itemId)
            {
                R.id.Accepted -> {
                    startActivity(Intent(applicationContext, Accepted::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }

                R.id.Request -> {
                    startActivity(Intent(applicationContext, Request::class.java))
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