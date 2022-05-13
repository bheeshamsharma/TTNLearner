package com.psgpw.geek_ttn.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.adapters.CourseAdapter
import com.psgpw.geek_ttn.adapters.TopicAdapter
import com.psgpw.geek_ttn.data.dummymodel.Assignment
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.data.dummymodel.Topic
import com.psgpw.geek_ttn.databinding.FragmentCoursesBinding
import com.psgpw.geek_ttn.databinding.FragmentTopicListBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.geek_ttn.ui.TopicDetailActivity
import com.psgpw.geek_ttn.viewmodels.CourseViewModel

class TopicListFragment : Fragment(), TopicAdapter.ClickListener {
    lateinit var binding: FragmentTopicListBinding
    val viewModel: CourseViewModel by viewModels<CourseViewModel>()
    private lateinit var adapter: TopicAdapter
    private var adapterData: ArrayList<Topic> = ArrayList()
    private lateinit var recyclerViewChat: RecyclerView
    var isEnrolled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopicListBinding.inflate(inflater)
        recyclerViewChat = binding.recyclerView

        initview(binding)
        return binding.root
    }

    private fun initview(binding: FragmentTopicListBinding) {
        /*  adapterData.add(Course("Kotlin"))
          adapterData.add(Course("Data Store (Shared Preference)"))
          adapterData.add(Course("Collection"))
          adapterData.add(Course("Design Patterns"))
          adapterData.add(Course("Networking"))
          adapterData.add(Course("Security"))*/
        /*  adapterData.add(Course("Python"))
          adapterData.add(Course("Node"))*/
        adapter = TopicAdapter(requireContext()!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)
    }

    private fun callTopicListApi(courseId: String?) {
        viewModel.getTopicList(courseId!!)
        apiCourseListObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((activity as MainActivity).setuptoolbar(arguments?.getString("course_name")!!))
        isEnrolled = arguments?.getBoolean("isEnrolled")!!
        callTopicListApi(arguments?.getString("course_id"))

        //setUpToolBar()
    }

    override fun onItemClick(data: Topic?) {
        if (isEnrolled) {
            val bundle = bundleOf("topic_id" to data?.id, "topic_name" to data?.topic_name)
            findNavController().navigate(R.id.action_navigation_topic_to_subTopicFragment, bundle)
        } else {
            Toast.makeText(requireContext(), " Please enroll the course.", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onViewAssignmentClick(data: Topic?) {
        if (isEnrolled) {
            val assignmentArray = parseArray(data?.assignment)
            val bundle = bundleOf(
                "assignment" to assignmentArray,
                "topic_name" to data?.topic_name,
                "topic_id" to data?.id
            )
            findNavController().navigate(R.id.action_navigation_topic_to_assignment, bundle)
        } else {
            Toast.makeText(requireContext(), " Please enroll the course.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun parseArray(assignment: List<Assignment>?): List<Assignment> {
        val assign = ArrayList<Assignment>()
        if (assignment != null && assignment.isNotEmpty()) {
            assignment?.forEach {
                val assignmentObj = Assignment(
                    id = it.id ?: "",
                    assignment_id = it.assignment_id ?: "",
                    user_id = it.user_id ?: "",
                    topic_id = it.topic_id ?: "",
                    assignment_name = it.assignment_name ?: "",
                    description = it.description ?: "",
                    assign_link = it.assign_link ?: "",
                    assignment_state = it.assignment_state ?: "",
                    align_expert = it.align_expert ?: ""
                )
                assign.add(assignmentObj)
            }

        }
        return assign
    }

    private fun apiCourseListObserver() {
        viewModel.topics.observe(this, Observer<ResultState<List<Topic>?>> {
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
}