package com.chandan.furever_care.Vet_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.User.Profile
import com.chandan.furever_care.User_Login.User
import com.chandan.furever_care.User_Login.User_login
import com.chandan.furever_care.Vet.Request
import com.chandan.furever_care.Vet.Vets
import com.chandan.furever_care.databinding.ActivityVetLoginBinding
import com.chandan.furever_care.databinding.ActivityVetRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Vet_Reg : AppCompatActivity() {

    private lateinit var binding: ActivityVetRegBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVetRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()
        binding.vRegBtn.setOnClickListener {
            val email = binding.vEmail.text.toString()
            val pass = binding.vPassword.text.toString()
            val number = binding.vPhoneno.text.toString()
            val username = binding.vName.text.toString()
            val qualification = binding.vQualiification.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && number.isNotEmpty() && username.isNotEmpty() && qualification.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        database = FirebaseDatabase.getInstance().getReference("Vets")
                        val uid = FirebaseAuth.getInstance().currentUser!!.uid
                        val Vets  = Vets(username,number,email,qualification)
                        database.child(uid).setValue(Vets).addOnCompleteListener { Toast.makeText(this,"Added",
                            Toast.LENGTH_SHORT).show() }
                        val intent =Intent(this, Request::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Please all the Information", Toast.LENGTH_SHORT).show()
            }

        }

        binding.vLgnBtn.setOnClickListener {
            startActivity(Intent(this,Vet_Login::class.java))
        }
    }
}