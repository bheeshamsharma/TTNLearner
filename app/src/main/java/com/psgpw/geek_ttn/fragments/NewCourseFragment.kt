package com.psgpw.geek_ttn.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.data.dummymodel.CourseRequest
import com.psgpw.geek_ttn.databinding.FragmentNewCourseBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.geek_ttn.viewmodels.CourseViewModel
import com.psgpw.pickapp.data.DataStoreManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewCourseFragment : Fragment() {
    lateinit var binding: FragmentNewCourseBinding
    val viewModel: CourseViewModel by viewModels<CourseViewModel>()
    lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewCourseBinding.inflate(inflater)
        initView()
        lifecycleScope.launch {
            DataStoreManager(requireContext()).getUserId().collect { id ->
                userId = id
            }
        }
        return binding.root
    }

    private fun callNewCourseApi(courseRequest: CourseRequest) {
        viewModel.newCourseRequest(courseRequest)
        apiNewCourseObserver()
    }

    private fun initView() {
        ((activity as MainActivity).setuptoolbar("Request Course"))
        binding.button.setOnClickListener {
            val courseName = binding.editTextTextPersonName.text.toString()
            val courseDesc = binding.editTextTextMultiLine2.text.toString()
            val expertMail = binding.editTextTextPersonName2.text.toString()
            if (courseName.isEmpty() || courseDesc.isEmpty() || expertMail.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter required inputs", Toast.LENGTH_SHORT)
                    .show()
            } else {
                callNewCourseApi(
                    courseRequest = CourseRequest(
                        userId,
                        courseName,
                        courseDesc,
                        expert_email = expertMail
                    )
                )
            }
        }

    }

    private fun apiNewCourseObserver() {
        viewModel.newCourse.observe(this, Observer<ResultState<Course?>> {
            when (it) {
                is ResultState.Loading -> binding.progress.progressBar.visibility = View.VISIBLE
                is ResultState.Error -> {
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ResultState.Success -> {
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Course Request Submitted", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().popBackStack()
                }
            }
        })
    }
}