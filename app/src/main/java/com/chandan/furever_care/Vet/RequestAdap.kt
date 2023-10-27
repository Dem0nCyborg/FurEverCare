package com.chandan.furever_care.Vet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chandan.furever_care.R
import com.chandan.furever_care.User.PetAdapter
import com.chandan.furever_care.User.PetData

class RequestAdap( val requestList : List<PetData>):RecyclerView.Adapter<RequestAdap.MyViewHolder>(){

    private lateinit var clickListner: onButtonClickListner
    interface onButtonClickListner{

        fun onClickAccept(position: Int)
        fun onClickDecline(position: Int)

    }

    fun setOnItemCLickListner(listner: onButtonClickListner){

        clickListner = listner

    }
    class MyViewHolder(itemView: View,listner: onButtonClickListner):RecyclerView.ViewHolder(itemView){

        val petImg: ImageView = itemView.findViewById(R.id.iv_petImg1)
        val petName: TextView = itemView.findViewById(R.id.tv_petName1)
        val petType: TextView = itemView.findViewById(R.id.tv_type1)
        val petAge: TextView = itemView.findViewById(R.id.tv_age1)
        val petGender: TextView = itemView.findViewById(R.id.tv_gender1)
        val accept : Button = itemView.findViewById(R.id.btn_accept)
        val decline : Button = itemView.findViewById(R.id.btn_decline)

        init {

            accept.setOnClickListener {
                listner.onClickAccept(adapterPosition)
            }

            decline.setOnClickListener {
                listner.onClickDecline(adapterPosition)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.request_list, parent, false)
        return RequestAdap.MyViewHolder(itemView,clickListner)
    }


    override fun getItemCount(): Int {
        return requestList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPet = requestList[position]

        holder.petName.text = currentPet.petName
        holder.petType.text = currentPet.petType
        holder.petAge.text = currentPet.petAge
        holder.petGender.text = currentPet.petGender



        if (currentPet.uri != null) {
            Glide.with(holder.itemView.context)
                .load(currentPet.uri)
                .into(holder.petImg)
        }
    }



}