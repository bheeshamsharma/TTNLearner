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
    val viewModel: LearningViewModel by viewModels<LearningViewModel>()

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var account: GoogleSignInAccount? = null

    private var isLogin = false

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
        lifecycleScope.launch {
            DataStoreManager(requireContext()).isUserLogin().collect { loginStatus ->
                isLogin = loginStatus
                if (isLogin) {
                    binding.tvGoogleLogin.setText("Logout")
                } else {
                    binding.tvGoogleLogin.setText("Google Login")
                }
            }
        }
        binding.tvGoogleLogin.setOnClickListener {
            if (isLogin) {
                logout()
            } else {
                signIn()
            }
        }

    }

    private fun signIn() {
        mGoogleSignInClient = GoogleLoginManager.getGoogleClient(requireActivity())

        account = GoogleLoginManager.getLastLoginAccount(requireContext())
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 100) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            account = completedTask.getResult(ApiException::class.java)
            Log.d("Learning Page : Name", account!!.displayName.toString())
            Log.d("Learning Page : Email", account!!.email.toString())
            callLoginApi(
                name = account!!.displayName.toString(),
                email = account!!.email.toString()
            )
            apiLoginSignUpObserver()
        } catch (e: ApiException) {
            Log.w("LoginActivity", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(
                requireContext(),
                "signInResult:failed code=\"" + e.statusCode,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private fun callLoginApi(name: String, email: String) {
        viewModel.signInRegisterUser(
            SignInRequest(
                name = name, email = email
            )
        )
    }

    private fun apiLoginSignUpObserver() {
        viewModel.loginState.observe(this, Observer<ResultState<ResponseData<User>>> {
            when (it) {
                is ResultState.Loading -> binding.progress.progressBar.visibility = View.VISIBLE
                is ResultState.Error -> {
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ResultState.Success -> {
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT).show()
                    Log.d("Login Data", it.data.toString())
                    lifecycleScope.launch {
                        DataStoreManager(context = requireContext()).saveUserToPreferencesStore(it.data.data)
                    }
                }
            }
        })
    }

    private fun logout() {
        lifecycleScope.launch {
            DataStoreManager(context!!).userLogout()
            signOutFromGoogle()
            binding.tvGoogleLogin.setText("Google Login")
            isLogin = false
        }
    }

    private fun signOutFromGoogle() {
        val mGoogleSignInClient = GoogleLoginManager.getGoogleClient(requireActivity())
        mGoogleSignInClient.signOut().addOnCompleteListener(requireActivity(), OnCompleteListener {

        })
    }
}