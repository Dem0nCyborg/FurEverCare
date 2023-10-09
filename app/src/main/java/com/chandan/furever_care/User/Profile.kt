package com.chandan.furever_care.User

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.Login0
import com.chandan.furever_care.R
import com.chandan.furever_care.User_Login.User_Reg
import com.chandan.furever_care.User_Login.User_login
import com.chandan.furever_care.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Users")
        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        readData(uid)

        binding.ownupdt.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this,Login0::class.java))
            finish()
        }



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




    private fun readData(uid: String) {
        runOnUiThread {
        val database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(uid).get().addOnSuccessListener { dataSnapshot ->
            val username = dataSnapshot.child("username").value
            val email = dataSnapshot.child("email").value
            val contact = dataSnapshot.child("phone").value
            val address = dataSnapshot.child("address").value


                binding.ownname.text = username?.toString()
                binding.ownemail.text = email?.toString()
                binding.owncontct.text = contact?.toString()
                binding.ownadd.text = address?.toString()
            }
        }
    }
}
