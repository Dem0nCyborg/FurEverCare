package com.chandan.furever_care.User

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.R
import com.chandan.furever_care.User_Login.User_Reg
import com.chandan.furever_care.databinding.ActivityProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("Username").toString()
        val username1 = intent.getStringExtra("Username1").toString()

        readData(username)



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



    private fun readData(username : String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(username).get().addOnSuccessListener {
            val username = it.child("username").value
            val email = it.child("email").value
            val contact = it.child("phone").value
            val address = it.child("address").value

            binding.ownname.text = username.toString()
            binding.ownemail.text = email.toString()
            binding.owncontct.text = contact.toString()
            binding.ownadd.text = address.toString()

        }

    }
}