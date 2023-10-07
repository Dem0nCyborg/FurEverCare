package com.chandan.furever_care.User_Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.User.MainActivity
import com.chandan.furever_care.User.Profile
import com.chandan.furever_care.databinding.ActivityUserRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class User_Reg : AppCompatActivity() {

    private lateinit var binding: ActivityUserRegBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uLgnBtn.setOnClickListener {
            startActivity(Intent(this,User_login::class.java))
            Toast.makeText(this,"Continue to login",Toast.LENGTH_SHORT).show()
            finish()
        }

        firebaseAuth=FirebaseAuth.getInstance()
        binding.uRegBtn.setOnClickListener {
            val email = binding.uEmail.text.toString()
            val pass = binding.uPassword.text.toString()
            val number = binding.uPhoneno.text.toString()
            val username = binding.uFn.text.toString()
            val address = binding.uAd.text.toString()



            if(email.isNotEmpty() && pass.isNotEmpty() && number.isNotEmpty() && username.isNotEmpty() && address.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        database = FirebaseDatabase.getInstance().getReference("Users")
                        val User  = User(username,number,email,address)
                        database.child(username).setValue(User).addOnCompleteListener { Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show() }
                        val intent =Intent(this,Profile::class.java)
                        val intent1 = Intent(this,User_login::class.java)
                        intent1.putExtra("Username",username)
                        intent.putExtra("Username",username)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Please all the Information",Toast.LENGTH_SHORT).show()
            }

        }

    }
}