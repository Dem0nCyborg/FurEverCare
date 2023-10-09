package com.chandan.furever_care.Vet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandan.furever_care.Login0
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityVetProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class VetProfile : AppCompatActivity() {

    private lateinit var binding: ActivityVetProfileBinding
    private lateinit var database : DatabaseReference

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


        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this,Login0::class.java))
            finish()
        }

        database = FirebaseDatabase.getInstance().getReference("Users")
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        readData(uid)


    }

    private fun readData(uid: String) {
        runOnUiThread {
            val database = FirebaseDatabase.getInstance().getReference("Vets")
            database.child(uid).get().addOnSuccessListener { dataSnapshot ->
                val username = dataSnapshot.child("username").value
                val email = dataSnapshot.child("email").value
                val contact = dataSnapshot.child("phone").value
                val qualification = dataSnapshot.child("qualification").value


                binding.vetname.text = username?.toString()
                binding.vetemail.text = email?.toString()
                binding.vetcontct.text = contact?.toString()
                binding.vetqualifications.text = qualification?.toString()
            }
        }
    }

}