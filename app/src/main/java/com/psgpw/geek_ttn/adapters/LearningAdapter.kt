package com.psgpw.geek_ttn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.data.dummymodel.Learning

class LearningAdapter(val context: Context, var listener: ClickListener, var list: List<Course>) :
    RecyclerView.Adapter<LearningAdapter.ViewHolder>() {

    interface ClickListener {
        fun onItemClick(data: Course?)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cousrename: TextView = view.findViewById(R.id.cousrename)
        var rating: RatingBar = view.findViewById(R.id.rating)
        var pro: ProgressBar = view.findViewById(R.id.progressBar)

        init {

            // Define click listener for the ViewHolder's View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_learning, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.cousrename.text = item.course_name
        holder.rating.rating = item.rating.toFloat()
       // holder.pro.progress = item.progrees
        holder.pro.progress = (10..100).random()

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}