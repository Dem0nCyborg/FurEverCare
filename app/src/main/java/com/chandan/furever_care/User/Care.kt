package com.chandan.furever_care.User

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityCareBinding
import java.text.SimpleDateFormat
import java.util.*

class Care : AppCompatActivity() {

    private lateinit var binding: ActivityCareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCareBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.floatingActionButton.setOnClickListener{
            addDate()
        }

    }

    private fun addDate() {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month =calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val dpd =DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, year0, month0, dayOfMonth0 ->
                val date = "$dayOfMonth0/${1+month0}/$year0"
                //val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                //val selecteddate = sdf.parse(date)
                Toast.makeText(this,"$date",Toast.LENGTH_SHORT).show()
            },
            day,month,year
            )
        dpd.datePicker.minDate = System.currentTimeMillis() + 86400000
        dpd.show()

    }
}