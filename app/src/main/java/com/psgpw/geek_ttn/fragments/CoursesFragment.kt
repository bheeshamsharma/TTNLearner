package com.psgpw.geek_ttn.fragments

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.adapters.ChatAdapter
import com.psgpw.geek_ttn.adapters.courseAdapter
import com.psgpw.geek_ttn.data.dummymodel.coursescreen
import com.psgpw.geek_ttn.databinding.FragmentCoursesBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.pickapp.data.models.ChatUser

class CoursesFragment : Fragment(),courseAdapter.ClickListener  {
    lateinit var binding: FragmentCoursesBinding
    private lateinit var adapter: courseAdapter
    private var adapterData: ArrayList<coursescreen> = ArrayList()
    private lateinit var recyclerViewChat: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoursesBinding.inflate(inflater)
        recyclerViewChat = binding.recyclerView

        initview(binding)
        return binding.root
    }

    private fun initview(binding: FragmentCoursesBinding) {
        adapterData.add(coursescreen("Android"))
        adapterData.add(coursescreen("IOS"))
        adapterData.add(coursescreen("React"))
        adapterData.add(coursescreen("Java"))
        adapterData.add(coursescreen("Python"))
        adapterData.add(coursescreen("Node"))
        adapter = courseAdapter(context!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((activity as MainActivity).setuptoolbar("Course "))

        //setUpToolBar()
    }

    override fun onItemClick(data: coursescreen?) {
        TODO("Not yet implemented")
    }

    /* private fun setUpToolBar() {
         binding.toolbar.tvTitle.setText("Settings")
         binding.toolbar.ivBack.setOnClickListener {
             findNavController().popBackStack()
         }
     }*/
}