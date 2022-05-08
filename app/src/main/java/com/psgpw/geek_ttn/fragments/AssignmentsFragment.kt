package com.psgpw.geek_ttn.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import assignmentadapter
import com.psgpw.geek_ttn.adapters.courseAdapter
import com.psgpw.geek_ttn.data.dummymodel.coursescreen
import com.psgpw.geek_ttn.databinding.FragmentAssignmentsBinding
import com.psgpw.geek_ttn.databinding.FragmentCoursesBinding

class AssignmentsFragment : Fragment(),assignmentadapter.ClickListener  {
    lateinit var binding: FragmentAssignmentsBinding
    private lateinit var adapter: assignmentadapter
    private var adapterData: ArrayList<coursescreen> = ArrayList()
    private lateinit var recyclerViewChat: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAssignmentsBinding.inflate(inflater)
        recyclerViewChat = binding.recyclerView

        initview(binding)
        return binding.root
    }

    private fun initview(binding: FragmentAssignmentsBinding) {
        adapterData.add(coursescreen("Assignment 1"))
        adapterData.add(coursescreen("Assignment 2"))
        adapterData.add(coursescreen("Assignment 3"))
        adapterData.add(coursescreen("Assignment 4"))
        adapterData.add(coursescreen("Assignment 5"))
        adapterData.add(coursescreen("Assignment 6"))
        adapter = assignmentadapter(context!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClick(data: coursescreen?) {
        TODO("Not yet implemented")
    }

}