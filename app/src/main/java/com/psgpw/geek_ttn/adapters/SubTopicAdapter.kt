package com.psgpw.geek_ttn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.SubTopic
import com.psgpw.geek_ttn.data.dummymodel.Topic

class SubTopicAdapter(
    val context: Context,
    var listener: ClickListener,
    var list: List<SubTopic>
) :
    RecyclerView.Adapter<SubTopicAdapter.ViewHolder>() {
    interface ClickListener {
        fun onItemClick(data: SubTopic?)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvname: TextView = view.findViewById(R.id.tv_name)
        var tvDesc: TextView = view.findViewById(R.id.tv_desc)

        init {

            // Define click listener for the ViewHolder's View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list, parent, false)
        return SubTopicAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubTopicAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.tvname.text = item.stopic_name
        holder.tvDesc.text = item.description

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}