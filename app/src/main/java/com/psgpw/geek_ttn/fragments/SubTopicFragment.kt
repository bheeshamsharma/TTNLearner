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
import com.psgpw.geek_ttn.adapters.SubTopicAdapter
import com.psgpw.geek_ttn.adapters.TopicAdapter
import com.psgpw.geek_ttn.data.dummymodel.SubTopic
import com.psgpw.geek_ttn.data.dummymodel.Topic
import com.psgpw.geek_ttn.databinding.FragmentSubTopicBinding
import com.psgpw.geek_ttn.databinding.FragmentTopicListBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.geek_ttn.ui.TopicDetailActivity
import com.psgpw.geek_ttn.viewmodels.CourseViewModel


class SubTopicFragment : Fragment(), SubTopicAdapter.ClickListener {
    lateinit var binding: FragmentSubTopicBinding
    val viewModel: CourseViewModel by viewModels<CourseViewModel>()
    private lateinit var adapter: SubTopicAdapter
    private var adapterData: ArrayList<SubTopic> = ArrayList()
    private lateinit var recyclerViewChat: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubTopicBinding.inflate(inflater)
        recyclerViewChat = binding.recyclerView

        initview()
        return binding.root
    }

    private fun initview() {
        adapter = SubTopicAdapter(requireContext()!!, this, adapterData)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)
    }

    private fun callTopicListApi(courseId: String?) {
        viewModel.getSubTopicList(courseId!!)
        apiCourseListObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ((activity as MainActivity).setuptoolbar(arguments?.getString("topic_name")!!))

        callTopicListApi(arguments?.getString("topic_id"))
        //setUpToolBar()
    }

    override fun onItemClick(data: SubTopic?) {
        val bundle = bundleOf("subTopic" to data)
        val intent =  Intent(requireActivity(), TopicDetailActivity::class.java)
        intent.putExtra("detail",bundle)
        startActivity(intent)
        // findNavController().navigate(R.id.action_navigation_topic_to_subTopicFragment,bundle)
    }

    private fun apiCourseListObserver() {
        viewModel.subTopics.observe(this, Observer<ResultState<List<SubTopic>?>> {
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