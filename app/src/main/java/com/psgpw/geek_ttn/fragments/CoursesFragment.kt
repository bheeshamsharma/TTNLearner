package com.psgpw.geek_ttn.fragments

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.adapters.ChatAdapter
import com.psgpw.geek_ttn.adapters.CourseAdapter
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.databinding.FragmentCoursesBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.geek_ttn.ui.PlayerActivity
import com.psgpw.geek_ttn.ui.TopicDetailActivity
import com.psgpw.geek_ttn.viewmodels.CourseViewModel
import com.psgpw.geek_ttn.viewmodels.LoginViewModel
import com.psgpw.pickapp.data.DataStoreManager
import com.psgpw.pickapp.data.models.ChatUser
import com.psgpw.pickapp.data.models.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.http.POST

class CoursesFragment : Fragment(), CourseAdapter.ClickListener {
    lateinit var binding: FragmentCoursesBinding
    val viewModel: CourseViewModel by viewModels<CourseViewModel>()

    lateinit var selectedCourse: Course
    private var postition: Int = 0
    lateinit var userId: String
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
        lifecycleScope.launch {
            DataStoreManager(requireContext()).getUserId().collect { id ->
                userId = id
            }
        }
        return binding.root
    }

    private fun initview(binding: FragmentCoursesBinding) {

        /* adapterData.add(Course("Android(With Kotlin)"))
         adapterData.add(Course("IOS(With Swift)"))
         adapterData.add(Course("React Native"))
         adapterData.add(Course("Java(Web Development)"))*/
        /*  adapterData.add(Course("Python"))
          adapterData.add(Course("Node"))*/
        adapter = CourseAdapter(requireContext()!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)

        binding.button4.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_learning_to_newCourseFragment)
        }
    }

    private fun callCourseListApi() {
        viewModel.getCourseList()
        apiCourseListObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((activity as MainActivity).setuptoolbar("Courses"))
        callCourseListApi()
        //setUpToolBar()
    }

    override fun onItemClick(data: Course?) {
        val bundle = bundleOf("course_id" to data?.id, "course_name" to data?.course_name, "isEnrolled" to data?.enroll)
        findNavController().navigate(R.id.action_navigation_course_to_topicFragment, bundle)
    }

    override fun onEnrollNowClick(postition: Int, data: Course?) {
        this.postition = postition
        selectedCourse = data!!
        callEnrollNowAPI()
    }

    private fun callEnrollNowAPI() {
        viewModel.enrollCourseRequest(userId, selectedCourse.id)
        apiEnrollCourseObserver()
    }

    private fun apiEnrollCourseObserver() {
        viewModel.enrollCourse.observe(this, Observer<ResultState<Course?>> {
            when (it) {
                is ResultState.Loading -> binding.progress.progressBar.visibility = View.VISIBLE
                is ResultState.Error -> {
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ResultState.Success -> {
                    binding.progress.progressBar.visibility = View.GONE
                    if (it.data != null) {
                        selectedCourse.enroll = true
                        adapter.notifyItemChanged(postition, selectedCourse)
                    }
                }
            }
        })
    }

    private fun apiCourseListObserver() {
        viewModel.courses.observe(this, Observer<ResultState<List<Course>?>> {
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