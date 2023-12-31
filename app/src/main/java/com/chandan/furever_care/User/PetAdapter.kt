package com.chandan.furever_care.User

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chandan.furever_care.R
import com.squareup.picasso.Picasso

class PetAdapter( val petList: List<PetData>) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val petImg: ImageView = itemView.findViewById(R.id.iv_petImg)
        val petName: TextView = itemView.findViewById(R.id.tv_petName)
        val petType: TextView = itemView.findViewById(R.id.tv_type)
        val petAge: TextView = itemView.findViewById(R.id.tv_age)
        val petGender: TextView = itemView.findViewById(R.id.tv_gender)
        val requestStatus: TextView = itemView.findViewById(R.id.tv_status)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pets_list, parent, false)
        return PetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val currentPet = petList[position]

        holder.petName.text = currentPet.petName
        holder.petType.text = currentPet.petType
        holder.petAge.text = currentPet.petAge
        holder.petGender.text = currentPet.petGender
        holder.requestStatus.text = currentPet.status


        if (currentPet.uri != null) {
            Glide.with(holder.itemView.context)
                .load(currentPet.uri)
                .into(holder.petImg)
        }

    }

    override fun getItemCount(): Int {
        return petList.size
    }
}
