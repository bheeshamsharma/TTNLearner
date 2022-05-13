package com.psgpw.geek_ttn.ui

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.models.request.LoginRequest
import com.psgpw.geek_ttn.managers.GoogleLoginManager
import com.psgpw.pickapp.data.DataStoreManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    var toolbar: Toolbar? = null
    var title: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar_gchannel)
        title = toolbar?.findViewById<TextView>(R.id.toolbar_title)
        toolbar?.findViewById<ImageView>(R.id.logout)?.setOnClickListener {
            showDialog()
        }

        setSupportActionBar(findViewById(R.id.toolbar_gchannel))
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        setuptoolbar("Learning")


        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home)
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController)
        val actionBar: ActionBar? = supportActionBar
        //actionBar!!.setTitle("My Learning")
    }

    public fun setuptoolbar(s: String) {

        if (toolbar != null) {
            title?.setText(s);
            //setSupportActionBar(toolbar);
            //supportActionBar!!.title = s
        }

    }

    private fun showDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View = LayoutInflater.from(applicationContext)
            .inflate(R.layout.dialog_logout, viewGroup, false)
        builder.setView(dialogView)
        val textView = TextView(this)
        textView.text = "Logout"
        textView.setPadding(20, 30, 20, 30)
        textView.textSize = 20f
        textView.setBackgroundColor(resources.getColor(R.color.primary_varient))
        textView.setTextColor(Color.WHITE)
        builder.setCustomTitle(textView)
        // builder.setMessage("Are you sure ? You  want to logout?")
        builder.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, which ->
            logout()
        })

        builder.setNegativeButton("NO", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun logout() {
        lifecycleScope.launch {
            DataStoreManager(this@MainActivity).userLogout()
            signOutFromGoogle()
        }
        startActivity(
            Intent(
                this@MainActivity,
                LoginActivity::class.java
            )
        )
        finish()
    }

    private fun signOutFromGoogle() {
        val mGoogleSignInClient = GoogleLoginManager.getGoogleClient(this)
        mGoogleSignInClient.signOut().addOnCompleteListener(this, OnCompleteListener {

        })
    }


}