package com.psgpw.geek_ttn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.Topic

class TopicAdapter(
    val context: Context,
    var listener: ClickListener,
    var list: List<Topic>
) :
    RecyclerView.Adapter<TopicAdapter.ViewHolder>() {
    interface ClickListener {
        fun onItemClick(data: Topic?)
        fun onViewAssignmentClick(data: Topic?)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvname: TextView = view.findViewById(R.id.tv_name)
        var tvDesc: TextView = view.findViewById(R.id.tv_desc)
        var btnEnroll: Button = view.findViewById(R.id.enroll_now)
        var rating: RatingBar = view.findViewById(R.id.rating)

        init {

            // Define click listener for the ViewHolder's View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list, parent, false)
        return TopicAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.tvname.text = item.topic_name
        holder.tvDesc.text = item.description

        holder.rating.visibility = View.GONE
        holder.btnEnroll.text = "View & Submit Assignment"

        holder.btnEnroll.setOnClickListener {
            listener.onViewAssignmentClick(item)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}