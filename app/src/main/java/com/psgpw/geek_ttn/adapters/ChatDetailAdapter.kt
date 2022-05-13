package com.psgpw.geek_ttn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.models.Message
import com.psgpw.pickapp.data.models.ChatUser

class ChatDetailAdapter(
    val context: Context,
    var list: List<Message>
) :
    RecyclerView.Adapter<ChatDetailAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvfirst: TextView = view.findViewById(R.id.tv_first)
        var tvSecond: TextView = view.findViewById(R.id.tv_second)

        init {

            // Define click listener for the ViewHolder's View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_chat_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        if (item.type == 1) {
            holder.tvSecond.visibility = View.GONE
            holder.tvfirst.text = item.message
        } else {
            holder.tvfirst.visibility = View.GONE
            holder.tvSecond.text = item.message
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}