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
import com.psgpw.geek_ttn.databinding.FragmentChatBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.pickapp.data.models.ChatUser

class ChatFragment : Fragment(),ChatAdapter.ClickListener {
    lateinit var binding: FragmentChatBinding

    private lateinit var adapter: ChatAdapter
    private var adapterData: ArrayList<ChatUser> = ArrayList()
    private lateinit var recyclerViewChat: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater)
        recyclerViewChat = binding.rvChat
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterData.add(ChatUser("Expert: 1","Learner 1 : Hi Sir, I have done my assignments"))
        adapterData.add(ChatUser("Expert: 2","Learner 2 : Thank you ."))
        adapterData.add(ChatUser("Expert: 3","Learner 3 : Can you help me"))
        adapterData.add(ChatUser("Expert: 4","Learner 4 :  Its OK!"))
        adapter = ChatAdapter(requireContext()!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)
        ((activity as MainActivity).setuptoolbar("Chat "))

    }

    override fun onItemClick(data: ChatUser?) {

    }
}