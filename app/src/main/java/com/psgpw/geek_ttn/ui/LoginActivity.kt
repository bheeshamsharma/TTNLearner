package com.psgpw.geek_ttn.ui

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.models.request.LoginRequest
import com.psgpw.geek_ttn.databinding.ActivityLoginBinding
import com.psgpw.geek_ttn.managers.GoogleLoginManager
import com.psgpw.geek_ttn.viewmodels.LoginViewModel
import com.psgpw.pickapp.data.DataStoreManager
import com.psgpw.pickapp.data.models.SignInRequest
import com.psgpw.pickapp.data.models.User
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    val viewModel: LoginViewModel by viewModels<LoginViewModel>()

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var account: GoogleSignInAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.tvGoogleLogin.setOnClickListener {
            signIn()
        }
        setContentView(binding.root)
    }

    private fun signIn() {
        mGoogleSignInClient = GoogleLoginManager.getGoogleClient(this)

        account = GoogleLoginManager.getLastLoginAccount(this@LoginActivity)
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

//            suspend {
//            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            account = completedTask.getResult(ApiException::class.java)
            Log.d("Learning Page : Name", account!!.displayName.toString())
            Log.d("Learning Page : Email", account!!.email.toString())

            val name = account!!.displayName.toString()
            val email = account!!.email.toString()
            //val accessToken = "ggjgjgugjgjgjgjjgjgjgggjggjgjggjgjjjjjgggjg"
            val accessToken = account!!.idToken.toString()
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val dialogView: View = LayoutInflater.from(applicationContext)
                .inflate(R.layout.dialog_choose_role, viewGroup, false)
            builder.setView(dialogView)
            dialogView.findViewById<TextView>(R.id.welcomename).text =
                ("Welcome $name , to the learning world")
            val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radioGroup)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            dialogView.findViewById<TextView>(R.id.button3).setOnClickListener {
                val selectedID = radioGroup.checkedRadioButtonId
                if (selectedID != -1) {
                    val radioButton = dialogView.findViewById<RadioButton>(selectedID)
                    val loginRequest: LoginRequest
                    if (radioButton.text.equals("Learner")) {
                        loginRequest = LoginRequest(
                            username = name,
                            email = email,
                            access_token = accessToken,
                            is_learner = true,
                            is_expert = false
                        )
                    } else {
                        loginRequest = LoginRequest(
                            email = email,
                            username = name,
                            access_token = accessToken,
                            is_learner = false,
                            is_expert = true
                        )
                    }
                    callLoginApi(loginRequest)
                    apiLoginSignUpObserver()
                    alertDialog.dismiss()
                } else {
                    Toast.makeText(
                        this,
                        "Please select your Role.",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }


        } catch (e: ApiException) {
            Log.w("LoginActivity", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(
                this,
                "signInResult:failed code=\"" + e.statusCode,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private fun callLoginApi(loginRequest: LoginRequest) {
        viewModel.signIn(loginRequest)
    }

    private fun apiLoginSignUpObserver() {
        viewModel.loginState.observe(this, Observer<ResultState<User?>> {
            when (it) {
                is ResultState.Loading -> binding.progress.progressBar.visibility = View.VISIBLE
                is ResultState.Error -> {
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ResultState.Success -> {
                    binding.progress.progressBar.visibility = View.GONE
                    //Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    Log.d("Login Data", it.data.toString())
                    lifecycleScope.launch {
                        DataStoreManager(context = this@LoginActivity).saveUserToPreferencesStore(it.data!!)
                    }
                    openMainActivity()
                }
            }
        })
    }

    private fun openMainActivity() {
        startActivity(
            Intent(
                this@LoginActivity,
                MainActivity::class.java
            )
        )
        finish()
    }


}