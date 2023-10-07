package com.chandan.furever_care.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var shortsList : ArrayList<Shorts_Data>
    private lateinit var shortsAdapter: ShortsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btmNavBar.setSelectedItemId(R.id.home)
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

                R.id.care -> {
                    startActivity(Intent(applicationContext,Care::class.java))
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

        shortsList()

    }

    private fun shortsList(){
        val recyclerView = binding.shortsRv
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        shortsList = ArrayList()

        addDataToList()

        shortsAdapter = ShortsAdapter(shortsList)
        recyclerView.adapter = shortsAdapter

    }

    private fun addDataToList(){
        shortsList.add(Shorts_Data(R.drawable.pets_bg_img,"Pets"))
        shortsList.add(Shorts_Data(R.drawable.pets_bg_img,"Dogs"))
        shortsList.add(Shorts_Data(R.drawable.pets_bg_img,"Cats"))
        shortsList.add(Shorts_Data(R.drawable.pets_bg_img,"Birds"))
    }

}