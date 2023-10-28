package com.chandan.furever_care.Vet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chandan.furever_care.R
import com.chandan.furever_care.User.PetData

class AppoinmentAdap (val appoinmentList: List<PetData>):RecyclerView.Adapter<AppoinmentAdap.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppoinmentAdap.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.appoinment_list, parent, false)
        return AppoinmentAdap.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPet = appoinmentList[position]

        holder.petName.text = currentPet.petName
        holder.petType.text = currentPet.petType
        holder.petAge.text = currentPet.petAge
        holder.petGender.text = currentPet.petGender
        holder.desc.text = currentPet.probDesc



        if (currentPet.uri != null) {
            Glide.with(holder.itemView.context)
                .load(currentPet.uri)
                .into(holder.petImg)
        }
    }

    override fun getItemCount(): Int {
        return appoinmentList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val petImg: ImageView = itemView.findViewById(R.id.petImg)
        val petName: TextView = itemView.findViewById(R.id.petName)
        val petType: TextView = itemView.findViewById(R.id.type)
        val petAge: TextView = itemView.findViewById(R.id.age)
        val petGender: TextView = itemView.findViewById(R.id.gender)
        val desc : TextView = itemView.findViewById(R.id.description)
    }
}