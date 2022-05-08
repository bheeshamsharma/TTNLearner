package com.psgpw.geek_ttn.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.adapters.mylearingadapter
import com.psgpw.geek_ttn.data.dummymodel.mylearning
import com.psgpw.geek_ttn.databinding.FragmentLearningBinding
import com.psgpw.geek_ttn.ui.MainActivity

class LearningFragment : Fragment(),mylearingadapter.ClickListener {
    lateinit var binding: FragmentLearningBinding
    private lateinit var adapter: mylearingadapter


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
         var adapterData: ArrayList<mylearning> = ArrayList()

        adapterData.add(mylearning("Enroll First Course","4/5",40))
        adapterData.add(mylearning("Enroll Second Course","3/5",90))
        adapterData.add(mylearning("Enroll Third Course","2/5",60))
        adapterData.add(mylearning("Enroll Fouth Course","4/5",20))
        adapterData.add(mylearning("Enroll Fifty Course","4/5",70))
        adapterData.add(mylearning("Enroll First Course","4/5",10))
        adapterData.add(mylearning("Enroll Last Course","4/5",80))
        adapter = mylearingadapter(requireContext(), this, adapterData)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.enroll.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_learning_to_newCourseFragment)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((activity as MainActivity).setuptoolbar("Learning "))


    }

    override fun onItemClick(data: mylearning?) {
//        TODO("Not yet implemented")
    }
}