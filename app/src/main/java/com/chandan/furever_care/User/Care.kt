package com.chandan.furever_care.User

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityCareBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.FirebaseStorageKtxRegistrar
import com.google.firebase.storage.ktx.storage

class Care : AppCompatActivity() {

    private lateinit var binding: ActivityCareBinding

    //Firebase
    private var storageRef = Firebase.storage
    private lateinit var uri : Uri

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCareBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val petTypes = resources.getStringArray(R.array.animalTypes)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,petTypes)
        binding.acTv.setAdapter(adapter)


        storageRef = FirebaseStorage.getInstance()



        binding.btmNavBar.setSelectedItemId(R.id.care)
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

        binding.ivPet.setOnClickListener {
            pickImageFromGallery()
        }

        binding.btnReq.setOnClickListener {
            storageRef.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener { task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener {
                            val petName = binding.edName.text.toString()
                            val petType = binding.acTv.text.toString()
                            val age = binding.edAge.text.toString()
                            val gender = binding.edGender.text.toString()
                            val desc = binding.edDesc.text.toString()
                            val petData = PetDetails(it.toString(),petName,petType,age,gender,desc)
                            val userId = FirebaseAuth.getInstance().currentUser!!.uid

                            val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                            databaseReference.child(userId).child(petName).setValue(petData)
                                .addOnSuccessListener {
                                    binding.cardBookApp.visibility = View.GONE
                                    binding.edName.setText("")
                                    binding.acTv.setText("")
                                    binding.edAge.setText("")
                                    binding.edGender.setText("")
                                    binding.edDesc.setText("")
                                }
                        }

                }
        }

        binding.floatingActionButton.setOnClickListener{
                binding.cardBookApp.visibility = View.VISIBLE
        }

    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            uri = data?.data!!
            binding.ivPet.setImageURI(data?.data)
        }
    }


}