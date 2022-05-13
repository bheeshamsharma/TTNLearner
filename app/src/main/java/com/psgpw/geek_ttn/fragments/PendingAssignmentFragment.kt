package com.example.greekcompactapp.fragment

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.adapters.LearningAdapter
import com.psgpw.geek_ttn.data.dummymodel.Assignment
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.databinding.FragmentAssignmentsBinding
import com.psgpw.geek_ttn.databinding.FragmentPendingAssignmentBinding
import com.psgpw.geek_ttn.ui.MainActivity
import com.psgpw.geek_ttn.viewmodels.CourseViewModel
import com.psgpw.pickapp.data.DataStoreManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import android.widget.TextView

import assignmentadapter




class PendingAssignmentFragment : Fragment(), assignmentadapter.ClickListener {
    private lateinit var adapter: assignmentadapter
    lateinit var binding: FragmentPendingAssignmentBinding
    val viewModel: CourseViewModel by viewModels<CourseViewModel>()
    private var adapterData: ArrayList<Assignment> = ArrayList()
    private lateinit var recyclerViewChat: RecyclerView
    lateinit var userId: String
    lateinit var alertDialog: AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPendingAssignmentBinding.inflate(inflater)
        recyclerViewChat = binding.recyclerView

        lifecycleScope.launch {
            DataStoreManager(requireContext()).getUserId().collect { id ->
                userId = id
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val array = arguments?.getParcelableArrayList<Assignment>("assignment")
        val topicId = arguments?.getString("topic_id")
        val topicName = arguments?.getString("topic_name")
        ((activity as MainActivity).setuptoolbar(topicName!!))
        adapter = assignmentadapter(requireContext()!!, this, array!!)
        recyclerViewChat.adapter = adapter
        recyclerViewChat.layoutManager = LinearLayoutManager(context)


    }

    private fun apiCourseListObserver() {
        viewModel.submitAssignment.observe(this, Observer<ResultState<Assignment?>> {
            when (it) {
                is ResultState.Loading -> binding.progress.progressBar.visibility = View.VISIBLE
                is ResultState.Error -> {
                    alertDialog.dismiss()
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ResultState.Success -> {
                    alertDialog.dismiss()
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "You have successfully Submitted your Assignment",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        })
    }

    private fun showDialog(data: Assignment?) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val viewGroup = requireActivity().findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_submit_assignment, viewGroup, false)
        val edtLink = dialogView.findViewById<EditText>(R.id.edt_link)
        builder.setView(dialogView)
        val textView = TextView(context)
        textView.text = "Submit Assignment"
        textView.setPadding(20, 30, 20, 30)
        textView.textSize = 20f
        textView.setBackgroundColor(resources.getColor(R.color.primary_varient))
        textView.setTextColor(Color.WHITE)
        builder.setCustomTitle(textView)
        // builder.setMessage("Are you sure ? You  want to logout?")
        builder.setPositiveButton("Submit", DialogInterface.OnClickListener { dialog, which ->
            if (edtLink.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter link", Toast.LENGTH_LONG)
                    .show()
            } else {
                data?.assign_link = edtLink.text.toString()
                data?.assignment_state = "submitted"
                data?.align_expert = "5"
                data?.user_id = userId
                data?.assignment_id = data!!.id
                callSubmitAssignmentApi(data)
            }
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        alertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun callSubmitAssignmentApi(data: Assignment?) {
        viewModel.submitAssignment(data!!)
        apiCourseListObserver()
    }


    override fun onItemClick(data: Assignment?) {
        showDialog(data)
    }

}