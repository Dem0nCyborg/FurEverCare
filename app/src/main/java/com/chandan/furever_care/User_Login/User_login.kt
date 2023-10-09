package com.chandan.furever_care.User_Login

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.User.MainActivity
import com.chandan.furever_care.User.Profile
import com.chandan.furever_care.User.Shop
import com.chandan.furever_care.databinding.ActivityUserLoginBinding


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class User_login : AppCompatActivity() {

    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        binding.uRegBtn.setOnClickListener {
            startActivity(Intent(this,User_Reg::class.java))
            Toast.makeText(this,"Get yourself registered..!",Toast.LENGTH_SHORT).show()
        }

        val username1 = intent.getStringExtra("Username").toString()

        binding.uLgnBtn.setOnClickListener {
            val email = binding.uEmail.text.toString()
            val pass = binding.uPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent =Intent(this, Profile::class.java)
                        intent.putExtra("Username1",username1)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Please fill the information",Toast.LENGTH_SHORT).show()
            }

        }

    }

    public override fun onStart() {
        super.onStart()

        val currentUser = firebaseAuth.currentUser
        if (currentUser != null){
            val intent =Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}