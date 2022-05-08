package com.psgpw.geek_ttn.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.psgpw.geek_ttn.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
     var toolbar: Toolbar? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar_gchannel)
        setuptoolbar("d")

        setSupportActionBar(findViewById(R.id.toolbar_gchannel))

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home)
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController)
        val actionBar: ActionBar? = supportActionBar
       // actionBar!!.setTitle(s)
    }

    public fun setuptoolbar(s: String) {

        if (toolbar != null) {
            toolbar!!.setTitle(s);
            setSupportActionBar(toolbar);
        }

    }


}