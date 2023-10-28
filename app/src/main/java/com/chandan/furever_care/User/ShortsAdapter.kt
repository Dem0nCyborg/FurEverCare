package com.chandan.furever_care.User

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chandan.furever_care.R

class ShortsAdapter (private val ShortsList : List<Shorts_Data>):
    RecyclerView.Adapter<ShortsAdapter.ShortsViewHolder>() {

    private lateinit var mlistener : onItemClickListener
    interface onItemClickListener{
        fun onClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mlistener = listener
    }
    class ShortsViewHolder (itemView: View,listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val shortsImgView : ImageView = itemView.findViewById(R.id.list_img)
        val shortsNameTv : TextView = itemView.findViewById(R.id.list_tv)

        init {
            itemView.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shorts_list , parent ,false)
        return ShortsViewHolder(view,mlistener)
    }

    override fun getItemCount(): Int {
        return ShortsList.size
    }

    override fun onBindViewHolder(holder: ShortsViewHolder, position: Int) {
        val shorts = ShortsList[position]
        holder.shortsImgView.setImageResource(shorts.ShortsImg)
        holder.shortsNameTv.text = shorts.ShortsName

    }

}