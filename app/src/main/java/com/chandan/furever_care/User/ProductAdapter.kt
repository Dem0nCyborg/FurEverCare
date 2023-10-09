package com.chandan.furever_care.User

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chandan.furever_care.R

class ProductAdapter(private val productList : ArrayList<Shop_Items>) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {


    private var buttonClickListener: OnButtonClickListener? = null

    class MyViewHolder(itemView: View,listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {

        val productImg =  itemView.findViewById<ImageView>(R.id.product_img)
        val productName = itemView.findViewById<TextView>(R.id.tv_productName)
        val productDesc = itemView.findViewById<TextView>(R.id.tv_productDesc)
        val productMRP = itemView.findViewById<TextView>(R.id.tv_price)

        init {
            itemView.findViewById<Button>(R.id.btn_buy).setOnClickListener {
                listener.onButtonClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shop_item,parent,false)
        return MyViewHolder(itemView, buttonClickListener!!)

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = productList[position]
        holder.productImg.setImageResource(currentItem.productImg)
        holder.productName.text = currentItem.productName
        holder.productDesc.text = currentItem.productDesc
        holder.productMRP.text = currentItem.productMRP


    }


    //OnClick for Button
    interface OnButtonClickListener {
        fun onButtonClick(position: Int)
    }
        fun setOnButtonClickListener(listener: OnButtonClickListener) {
            buttonClickListener = listener
        }



}