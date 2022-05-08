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
import com.psgpw.geek_ttn.adapters.CourseAdapter
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.databinding.FragmentCoursesBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.pickapp.data.models.ChatUser

class CoursesFragment : Fragment(),CourseAdapter.ClickListener  {
    lateinit var binding: FragmentCoursesBinding
    private lateinit var adapter: CourseAdapter
    private var adapterData: ArrayList<Course> = ArrayList()
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
        adapterData.add(Course("Android"))
        adapterData.add(Course("IOS"))
        adapterData.add(Course("React"))
        adapterData.add(Course("Java"))
        adapterData.add(Course("Python"))
        adapterData.add(Course("Node"))
        adapter = CourseAdapter(requireContext()!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((activity as MainActivity).setuptoolbar("Course "))

        //setUpToolBar()
    }

    override fun onItemClick(data: Course?) {
//        TODO("Not yet implemented")
    }

    /* private fun setUpToolBar() {
         binding.toolbar.tvTitle.setText("Settings")
         binding.toolbar.ivBack.setOnClickListener {
             findNavController().popBackStack()
         }
     }*/
}