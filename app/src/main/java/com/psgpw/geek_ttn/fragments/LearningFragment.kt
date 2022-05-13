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
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.adapters.LearningAdapter
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.data.dummymodel.Learning
import com.psgpw.geek_ttn.databinding.FragmentLearningBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.geek_ttn.viewmodels.CourseViewModel
import com.psgpw.pickapp.data.DataStoreManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LearningFragment : Fragment(), LearningAdapter.ClickListener {
    lateinit var binding: FragmentLearningBinding
    private lateinit var adapter: LearningAdapter

    val viewModel: CourseViewModel by viewModels<CourseViewModel>()
    private var adapterData: ArrayList<Course> = ArrayList()
    lateinit var userId : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLearningBinding.inflate(inflater)
        initview(binding)
        lifecycleScope.launch {
            DataStoreManager(requireContext()).getUserId().collect { id ->
                userId = id
            }
        }
        return binding.root
    }

    private fun initview(binding: FragmentLearningBinding) {
        adapter = LearningAdapter(requireContext(), this, adapterData)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.enroll.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_learning_to_courseFragment)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((activity as MainActivity).setuptoolbar("Learning "))
        callCourseListApi()

    }

    private fun callCourseListApi() {
        viewModel.getEnrolledCourseList(userId)
        apiCourseListObserver()
    }

    private fun apiCourseListObserver() {
        viewModel.enrolledCourses.observe(this, Observer<ResultState<List<Course>?>> {
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

    override fun onItemClick(data: Course?) {
        val bundle = bundleOf("course_id" to data?.course_id, "course_name" to data?.course_name, "isEnrolled" to true)
        findNavController().navigate(R.id.action_navigation_course_to_topicFragment, bundle)
    }
}