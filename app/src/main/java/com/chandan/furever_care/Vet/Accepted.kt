package com.chandan.furever_care.Vet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chandan.furever_care.R
import com.chandan.furever_care.User.PetData
import com.chandan.furever_care.databinding.ActivityAcceptedBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Accepted : AppCompatActivity() {

    private lateinit var binding: ActivityAcceptedBinding

    private lateinit var dbref : DatabaseReference
    private lateinit var petRecyclerView: RecyclerView
    private lateinit var petArrayList: ArrayList<PetData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcceptedBinding.inflate(layoutInflater)
        setContentView(binding.root)




        petRecyclerView = binding.recyclerview
        petRecyclerView.layoutManager = LinearLayoutManager(this)
        petRecyclerView.hasFixedSize()
        petArrayList = arrayListOf()
        getpetData()




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

    private fun getpetData() {

        dbref = FirebaseDatabase.getInstance().getReference("Pets")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    petArrayList.clear()
                    for (userSnapshot in snapshot.children){
                        val uid = FirebaseAuth.getInstance().currentUser!!.uid
                        val pets = userSnapshot.getValue(PetData::class.java)
                        petArrayList.add(pets!!)
                        for(key in petArrayList){
                            if (key.status!!.contains("Rejected$uid",ignoreCase = true)){
                                petArrayList.remove(key)
                            }else if (key.status!!.contains("Accept$uid",ignoreCase = true)){
                                petArrayList.clear()
                                petArrayList.add(key)

                            }else{
                                petArrayList.remove(key)
                            }
                        }


                    }


                    var adapter = AppoinmentAdap(petArrayList)
                    petRecyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}