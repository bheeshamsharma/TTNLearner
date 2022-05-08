package com.psgpw.geek_ttn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.Course

class CourseAdapter(
    val context: Context,
    var listener: ClickListener,
    var list: List<Course>
) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {
    interface ClickListener {
        fun onItemClick(data: Course?)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvname: TextView = view.findViewById(R.id.tv_name)

        init {

            // Define click listener for the ViewHolder's View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list, parent, false)
        return CourseAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.tvname.text = item.name

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
