package com.psgpw.geek_ttn.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.psgpw.HireMe.data.ResponseData
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.databinding.FragmentLearningBinding
import com.psgpw.geek_ttn.managers.GoogleLoginManager
import com.psgpw.geek_ttn.viewmodels.LearningViewModel
import com.psgpw.pickapp.data.DataStoreManager
import com.psgpw.pickapp.data.models.SignInRequest
import com.psgpw.pickapp.data.models.User
import com.psgpw.pickapp.data.network.KEY_SOCIAL_LOGIN
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LearningFragment : Fragment() {
    lateinit var binding: FragmentLearningBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLearningBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}