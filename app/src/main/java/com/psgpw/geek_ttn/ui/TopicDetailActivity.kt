package com.psgpw.geek_ttn.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.databinding.ActivityTopicDetailBinding

class TopicDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopicDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarTitle.setText("Topic Detail");
        setSupportActionBar(binding.toolbarGchannel);
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        //supportActionBar!!.title = s


        binding.btnWatchVideo.setOnClickListener {
            startActivity(
                Intent(
                    this@TopicDetailActivity,
                    PlayerActivity::class.java
                )
            )
        }

        binding.btnSubmitAssignment.setOnClickListener {
            startActivity(
                Intent(
                    this@TopicDetailActivity,
                    MainActivity::class.java
                )
            )
        }

        binding.btnChatExpert.setOnClickListener {
            startActivity(
                Intent(
                    this@TopicDetailActivity,
                    MainActivity::class.java
                )
            )
        }

    }

}