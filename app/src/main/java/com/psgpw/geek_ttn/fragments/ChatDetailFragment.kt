package com.psgpw.geek_ttn.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.adapters.ChatAdapter
import com.psgpw.geek_ttn.adapters.ChatDetailAdapter
import com.psgpw.geek_ttn.data.models.Message
import com.psgpw.geek_ttn.databinding.FragmentChatDetailBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.pickapp.data.models.ChatUser

class ChatDetailFragment : Fragment() {
    lateinit var binding: FragmentChatDetailBinding
    private lateinit var adapter: ChatDetailAdapter
    private var adapterData: ArrayList<Message> = ArrayList()
    private lateinit var recyclerViewChat: RecyclerView
    var type: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatDetailBinding.inflate(inflater)
        recyclerViewChat = binding.rvChat
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ChatDetailAdapter(requireContext()!!, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)
        ((activity as MainActivity).setuptoolbar(arguments?.getString("name")!!))

        binding.buttonGchatSend.setOnClickListener {
            if (!binding.editGchatMessage.text.isEmpty()) {
                val message = Message(binding.editGchatMessage.text.toString(), type)
                if (type == 1) {
                    type = 2
                } else {
                    type = 1
                }
                adapterData.add(message)
                adapter.notifyDataSetChanged()
                binding.editGchatMessage.setText("")
            }
        }

    }

}