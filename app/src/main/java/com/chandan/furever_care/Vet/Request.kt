package com.chandan.furever_care.Vet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chandan.furever_care.R
import com.chandan.furever_care.User.Care
import com.chandan.furever_care.User.MainActivity
import com.chandan.furever_care.User.PetAdapter
import com.chandan.furever_care.User.PetData
import com.chandan.furever_care.User.Profile
import com.chandan.furever_care.User.Shop
import com.chandan.furever_care.databinding.ActivityRequestBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class Request : AppCompatActivity() {

    private lateinit var binding: ActivityRequestBinding

    private lateinit var dbref : DatabaseReference
    private lateinit var petRecyclerView: RecyclerView
    private lateinit var petArrayList: ArrayList<PetData>
    private var storageRef = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        petRecyclerView = binding.recyclerview
        petRecyclerView.layoutManager = LinearLayoutManager(this)
        petRecyclerView.hasFixedSize()
        petArrayList = arrayListOf()
        getpetData()

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

    private fun getpetData() {

            dbref = FirebaseDatabase.getInstance().getReference("Pets")
            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        for (userSnapshot in snapshot.children){
                            val pets = userSnapshot.getValue(PetData::class.java)
                            petArrayList.add(pets!!)


                        }
                        petRecyclerView.adapter = RequestAdap(petArrayList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

    }
}