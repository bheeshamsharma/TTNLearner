package com.psgpw.geek_ttn.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.widget.RelativeLayout
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.psgpw.geek_ttn.R
import com.psgpw.pickapp.data.models.ChatUser

class ChatAdapter(
    val context: Context,
    var listener: ClickListener,
    var list: List<ChatUser>
) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    interface ClickListener {
        fun onItemClick(data: ChatUser?)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvname: TextView = view.findViewById(R.id.tv_name)
        var tvMessage: TextView = view.findViewById(R.id.tv_message)

        init {

            // Define click listener for the ViewHolder's View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvname.text = item.name
        holder.tvMessage.text = item.message

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}