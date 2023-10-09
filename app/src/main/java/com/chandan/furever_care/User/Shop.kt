package com.chandan.furever_care.User

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityShopBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class Shop : AppCompatActivity(),PaymentResultListener {

    private lateinit var binding: ActivityShopBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Shop_Items>
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btmNavBar.setSelectedItemId(R.id.shop)
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

        productList()



    }

    private fun productList() {
        recyclerView = binding.productRv
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        newArrayList = ArrayList()
        addDataToList()
        productAdapter = ProductAdapter(newArrayList)
        recyclerView.adapter = productAdapter


        productAdapter.setOnButtonClickListener(object : ProductAdapter.OnButtonClickListener{
            override fun onButtonClick(position: Int) {
                val mrp = newArrayList[position].productMRP
                    .replace("₹","")
                    .replace(".","")
                    .replace(" INR","")
                    .trim()

                Checkout.preload(applicationContext)
                val co = Checkout()
                co.setKeyID("rzp_test_eCUSn4OMmvjXEg")

                try {
                    val options = JSONObject()
                    options.put("name","Razorpay Corp")
                    options.put("description","FurEver Care")
                    //You can omit the image option to fetch the image from the dashboard
                    options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
                    options.put("theme.color", "#3399cc");
                    options.put("currency","INR");
                    options.put("amount","$mrp")//pass amount in currency subunits


                    val prefill = JSONObject()
                    prefill.put("email","")
                    prefill.put("contact","")

                    options.put("prefill",prefill)
                    co.open(this@Shop,options)
                }catch (e: Exception){
                    Toast.makeText(this@Shop,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }


        })

    }
    private fun addDataToList() {

        newArrayList.add(Shop_Items(R.drawable.pedigree_adult,"PEDIGREE ADULT","PEDIGREE complete and balanced dog food for adult dogs is a wholesome meal packed with essential nutrients vital to the healthy growth of your pet.","₹2375.00 INR"))
        newArrayList.add(Shop_Items(R.drawable.pedigree_puppy,"PEDIGREE PUPPY","Pedigree dog food is 100% vegetarian and a complete balanced food for puppy adult dogs.","₹1207.00 INR"))
        newArrayList.add(Shop_Items(R.drawable.rubber_chicken,"Yellow Screaming Rubber Chicken","Yellow is the predominant color. Screaming yellow rubber chicken squeaker chew toy for dogs Play with your wonderful pet with a squeaky chew toy.","₹199.00 INR"))
        newArrayList.add(Shop_Items(R.drawable.cat_food,"Whiskas Adult","Whiskas adult cat food is the nutrition that your cat needs for a healthy and active lifestyle and makes for an irresistible cat food for your special love","₹1696.00 INR"))
        newArrayList.add(Shop_Items(R.drawable.pedigree_pro,"PEDIGREE PRO","Pedigree Professional Range adult dog food fulfills the special needs of your dog.","₹ 1030.00 INR"))
        newArrayList.add(Shop_Items(R.drawable.pet_toys,"Pet Toys","Our dog toy set consists of 3 toys that are most popular with dogs: chew toys, cotton rope balls, which are in beautiful & vibrant colors, is great for medium or small dogs.","₹599.00 INR"))
        newArrayList.add(Shop_Items(R.drawable.pedigree_gravy,"PEDIGREE Gravy Chicken 2.1 kg (30x0.07 kg)","Give your newborn puppy all the love and nourishment that it needs with this Pedigree Wet Dog Food.","₹950.00 INR"))

    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this,"Payment Successful for $p0",Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this,"Payment Error for $p0",Toast.LENGTH_SHORT).show()
    }

}