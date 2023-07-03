package com.chandan.furever_care.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityShopBinding

class Shop : AppCompatActivity() {

    private lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btmNavBar.setSelectedItemId(R.id.shop)
        binding.btmNavBar.setOnItemSelectedListener { item ->
            when (item.itemId)
            {

                R.id.shop -> {
                    startActivity(Intent(applicationContext,Shop::class.java))
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
                R.id.care -> {
                    startActivity(Intent(applicationContext,Care::class.java))
                    overridePendingTransition(0,0)
                    finish()
                    true
                }
                R.id.profile -> {
                    startActivity(Intent(applicationContext,Profile::class.java))
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