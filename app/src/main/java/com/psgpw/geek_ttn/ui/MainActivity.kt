package com.psgpw.geek_ttn.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.psgpw.geek_ttn.R
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home)
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController)
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val cal: Calendar = Calendar.getInstance()
            val dynamicTitle: String = cal.getTime().toString()
            //Setting a dynamic title at runtime. Here, it displays the current time.
            actionBar.setTitle(dynamicTitle)
        }

    }

}