package com.chandan.furever_care.User

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chandan.furever_care.R
import com.chandan.furever_care.User.Constants.OPEN_SEARCH
import com.chandan.furever_care.User.Constants.RECEIVE_ID
import com.chandan.furever_care.User.Constants.SEND_ID
import com.chandan.furever_care.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.Timestamp

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var shortsList : ArrayList<Shorts_Data>
    private lateinit var shortsAdapter: ShortsAdapter

    private lateinit var adapter : MsgAdapter
    private val botList = listOf("Peter","Mario","WALL_E","Astra")

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

        message_rv()

        clickEvents()

        val random = (0..3).random()
         customMessage("Hello..! Today you're speaking with ${botList[random]}, how may I help you good master..?")

    }

    private fun clickEvents() {
        binding.btnSend.setOnClickListener {
            sendMessage()
        }

        binding.rvMessages.setOnClickListener{
            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main){
                    binding.rvMessages.scrollToPosition(adapter.itemCount-1)
                }

            }
        }

    }

    private fun message_rv() {
        adapter = MsgAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage(){
        val message = "search"+binding.etMessage.text.toString()
        val timestamp = com.chandan.furever_care.User.Time.timeStamp()

        if (message.isNotEmpty()) {
            binding.etMessage.setText("")

            adapter.insertMessage(Messages(message.substringAfter("search"), SEND_ID, timestamp))
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }   
    }

    private fun botResponse(message: String) {

        val timestamp = com.chandan.furever_care.User.Time.timeStamp()


        GlobalScope.launch {
            delay(1500)
            withContext(Dispatchers.Main){
                val response = BotResponse.basicResponse(message)
                adapter.insertMessage(Messages(response, RECEIVE_ID,timestamp))
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

                when(response){
                    OPEN_SEARCH -> {
                        val site  = Intent(Intent.ACTION_VIEW)
                        val searchTerm : String? = message.substringAfter("search")
                        site.data = Uri.parse("https://www.google.com/search?q=$searchTerm")
                        startActivity(site)
                    }
                }

            }
        }


    }

    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                var timestamp = com.chandan.furever_care.User.Time.timeStamp()
                adapter.insertMessage(Messages(message,RECEIVE_ID,timestamp))

                binding.rvMessages.scrollToPosition(adapter.itemCount-1)

            }
        }

    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                binding.rvMessages.scrollToPosition(adapter.itemCount-1)
            }
        }

    }

    private fun shortsList(){
        val recyclerView = binding.shortsRv
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        shortsList = ArrayList()

        addDataToList()

        shortsAdapter = ShortsAdapter(shortsList)
        recyclerView.adapter = shortsAdapter
        shortsAdapter.setOnItemClickListener(object : ShortsAdapter.onItemClickListener{
            override fun onClick(position: Int) {
                startActivity(Intent(this@MainActivity,Reels::class.java))
            }

        })
    }

    private fun addDataToList(){
        shortsList.add(Shorts_Data(R.drawable.pets_bg_img,"Pets"))
        shortsList.add(Shorts_Data(R.drawable.pets_bg_img,"Dogs"))
        shortsList.add(Shorts_Data(R.drawable.pets_bg_img,"Cats"))
        shortsList.add(Shorts_Data(R.drawable.pets_bg_img,"Birds"))
    }

}