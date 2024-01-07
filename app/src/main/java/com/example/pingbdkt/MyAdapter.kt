package com.example.pingbdkt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val context: Context, var list:List<CoursesItem>):RecyclerView.Adapter<MyAdapter.ViewHolderClass>() {

    class ViewHolderClass(itemView:View):RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.RV_Image)
        var tvName = itemView.findViewById<TextView>(R.id.RV_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ViewHolderClass(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        Glide.with(context).load(list[position].img).into(holder.img)
        holder.tvName.text = list[position].courseName
    }

}