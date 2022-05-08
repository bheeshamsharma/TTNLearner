package com.psgpw.geek_ttn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.mylearning
import com.psgpw.geek_ttn.fragments.LearningFragment
import com.psgpw.pickapp.data.models.ChatUser

class mylearingadapter(val context:Context,var listener:ClickListener,var list:List<mylearning>):
    RecyclerView.Adapter<mylearingadapter.ViewHolder>() {

    interface ClickListener {
        fun onItemClick(data: mylearning?)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cousrename: TextView = view.findViewById(R.id.cousrename)
        var rating: TextView = view.findViewById(R.id.rating)
        var pro: ProgressBar = view.findViewById(R.id.progressBar)

        init {

            // Define click listener for the ViewHolder's View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_learning, parent, false)
        return mylearingadapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.cousrename.text = item.name
        holder.rating.text = item.rating
        holder.pro.progress = item.progrees

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}