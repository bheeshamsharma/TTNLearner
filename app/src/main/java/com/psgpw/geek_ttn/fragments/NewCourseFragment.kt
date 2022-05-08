package com.example.greekcompactapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.databinding.FragmentNewCourseBinding
import com.psgpw.geek_ttn.ui.MainActivity

class NewCourseFragment : Fragment() {
    lateinit var bindind: FragmentNewCourseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindind = FragmentNewCourseBinding.inflate(inflater)
        initView()
        return bindind.root
    }

    private fun initView() {
        bindind.button.setOnClickListener {
            Toast.makeText(requireContext(), "course noted", Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(
                    requireContext(),
                    MainActivity::class.java
                )
            )
        }

    }
}