package com.psgpw.geek_ttn.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import assignmentadapter
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.Assignment
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.databinding.FragmentAssignmentsBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.geek_ttn.viewmodels.CourseViewModel
import com.psgpw.pickapp.data.DataStoreManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AssignmentsFragment : Fragment(), assignmentadapter.ClickListener {
    lateinit var binding: FragmentAssignmentsBinding
    val viewModel: CourseViewModel by viewModels<CourseViewModel>()

    private lateinit var adapter: assignmentadapter
    private var adapterData: ArrayList<Assignment> = ArrayList()
    private lateinit var recyclerViewChat: RecyclerView

    lateinit var userId: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAssignmentsBinding.inflate(inflater)
        recyclerViewChat = binding.recyclerView

        initview(binding)
        lifecycleScope.launch {
            DataStoreManager(requireContext()).getUserId().collect { id ->
                userId = id
            }
        }
        return binding.root
    }

    private fun initview(binding: FragmentAssignmentsBinding) {
        adapter = assignmentadapter(requireContext()!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((activity as MainActivity).setuptoolbar("Assignment "))
        callAssignmentListApi()

    }

    private fun callAssignmentListApi() {
        viewModel.getAssignmentList(userId)
        apiCourseListObserver()
    }

    private fun apiCourseListObserver() {
        viewModel.assignments.observe(this, Observer<ResultState<List<Assignment>?>> {
            when (it) {
                is ResultState.Loading -> binding.progress.progressBar.visibility = View.VISIBLE
                is ResultState.Error -> {
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ResultState.Success -> {
                    binding.progress.progressBar.visibility = View.GONE
                    adapterData.run {
                        adapterData.clear()
                        adapterData.addAll(it.data!!)
                        adapter.notifyDataSetChanged()
                    }

                }
            }
        })
    }


    override fun onItemClick(data: Assignment?) {

    }

}