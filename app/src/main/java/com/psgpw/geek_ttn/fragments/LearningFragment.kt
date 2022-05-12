package com.psgpw.geek_ttn.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.adapters.LearningAdapter
import com.psgpw.geek_ttn.data.dummymodel.Learning
import com.psgpw.geek_ttn.databinding.FragmentLearningBinding
import com.psgpw.geek_ttn.ui.MainActivity

class LearningFragment : Fragment(), LearningAdapter.ClickListener {
    lateinit var binding: FragmentLearningBinding
    private lateinit var adapter: LearningAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLearningBinding.inflate(inflater)
        initview(binding)
        return binding.root
    }

    private fun initview(binding: FragmentLearningBinding) {
        var adapterData: ArrayList<Learning> = ArrayList()

        adapterData.add(Learning("Android (With Kotlin) ", "4/5", 40))
        adapterData.add(Learning("Java (For Web Development)", "3/5", 90))
        adapterData.add(Learning("IOS (With Swift)", "2/5", 60))
        adapterData.add(Learning("Data Science", "4/5", 20))
        /*  adapterData.add(Learning("Enroll Fifty Course","4/5",70))
          adapterData.add(Learning("Enroll First Course","4/5",10))
          adapterData.add(Learning("Enroll Last Course","4/5",80))*/
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


    }

    override fun onItemClick(data: Learning?) {
//        TODO("Not yet implemented")
    }
}