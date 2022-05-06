package com.psgpw.geek_ttn.fragments

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.psgpw.geek_ttn.databinding.FragmentCoursesBinding

class CoursesFragment : Fragment() {
    lateinit var binding: FragmentCoursesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoursesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpToolBar()
    }

   /* private fun setUpToolBar() {
        binding.toolbar.tvTitle.setText("Settings")
        binding.toolbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }*/
}