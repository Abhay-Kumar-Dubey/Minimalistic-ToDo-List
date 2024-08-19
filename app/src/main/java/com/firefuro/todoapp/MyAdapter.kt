package com.firefuro.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val taskList: ArrayList<Tasks>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        taskList.size

        return TODO("Provide the return value")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.task.text=currentItem.tasks

    }
    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val task :TextView = itemView.findViewById(R.id.listText)

    }
}