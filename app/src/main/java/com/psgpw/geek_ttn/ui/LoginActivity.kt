package com.psgpw.geek_ttn.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.psgpw.geek_ttn.databinding.ActivityLoginBinding
import com.psgpw.geek_ttn.managers.GoogleLoginManager
import com.psgpw.geek_ttn.viewmodels.LearningViewModel
import com.psgpw.pickapp.data.DataStoreManager
import com.psgpw.pickapp.data.models.SignInRequest
import com.psgpw.pickapp.data.models.User
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    val viewModel: LearningViewModel by viewModels<LearningViewModel>()

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var account: GoogleSignInAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.tvGoogleLogin.setOnClickListener {
            signIn()
        }
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

    private  fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            account = completedTask.getResult(ApiException::class.java)
            Log.d("Learning Page : Name", account!!.displayName.toString())
            Log.d("Learning Page : Email", account!!.email.toString())
            callLoginApi(
                name = account!!.displayName.toString(),
                email = account!!.email.toString()
            )

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val dialogView: View = LayoutInflater.from(applicationContext).inflate(R.layout.dialog_choose_role, viewGroup, false)
            builder.setView(dialogView)
            var name1 = account!!.displayName.toString()

            dialogView.findViewById<TextView>(R.id.welcomename).text=("Welcome $name1 , to the learning world")
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
            dialogView.findViewById<TextView>(R.id.button3).setOnClickListener {
                startActivity(Intent(this,MainActivity::class.java))
                alertDialog.hide()
            }

//            var user:User= User("454",email = account!!.email.toString(),name = account!!.displayName.toString(),null)
//            DataStoreManager(context = this@LoginActivity).saveUserToPreferencesStore(user)
//            openRoleSelectionActivity()

            //apiLoginSignUpObserver()
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
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ResultState.Success -> {
                    binding.progress.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    Log.d("Login Data", it.data.toString())
                    lifecycleScope.launch {
                        DataStoreManager(context = this@LoginActivity).saveUserToPreferencesStore(it.data.data)
                    }
                    openRoleSelectionActivity()
                }
            }
        })
    }

    private fun openRoleSelectionActivity() {
        startActivity(
            Intent(
                this@LoginActivity,
                MainActivity::class.java
            )
        )
        finish()
    }

    private fun logout() {
        lifecycleScope.launch {
            DataStoreManager(this@LoginActivity).userLogout()
            signOutFromGoogle()
        }
    }

    private fun signOutFromGoogle() {
        val mGoogleSignInClient = GoogleLoginManager.getGoogleClient(this)
        mGoogleSignInClient.signOut().addOnCompleteListener(this, OnCompleteListener {

        })
    }
}