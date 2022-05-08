package com.psgpw.geek_ttn.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import assignmentadapter
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.databinding.FragmentAssignmentsBinding

class AssignmentsFragment : Fragment(),assignmentadapter.ClickListener  {
    lateinit var binding: FragmentAssignmentsBinding
    private lateinit var adapter: assignmentadapter
    private var adapterData: ArrayList<Course> = ArrayList()
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
        adapterData.add(Course("Assignment 1"))
        adapterData.add(Course("Assignment 2"))
        adapterData.add(Course("Assignment 3"))
        adapterData.add(Course("Assignment 4"))
        adapterData.add(Course("Assignment 5"))
        adapterData.add(Course("Assignment 6"))
        adapter = assignmentadapter(context!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClick(data: Course?) {

    }

}