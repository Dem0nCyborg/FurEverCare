package com.chandan.furever_care.Vet_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.User.MainActivity
import com.chandan.furever_care.User.Profile
import com.chandan.furever_care.Vet.Request
import com.chandan.furever_care.Vet.VetProfile
import com.chandan.furever_care.databinding.ActivityUserLoginBinding
import com.chandan.furever_care.databinding.ActivityVetLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Vet_Login : AppCompatActivity() {

    private lateinit var binding: ActivityVetLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVetLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.vRegBtn.setOnClickListener {
            startActivity(Intent(this,Vet_Reg::class.java))
        }

        binding.vLgnBtn.setOnClickListener {
            val email = binding.vEmail.text.toString()
            val pass = binding.vPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent =Intent(this, MainActivity::class.java)
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