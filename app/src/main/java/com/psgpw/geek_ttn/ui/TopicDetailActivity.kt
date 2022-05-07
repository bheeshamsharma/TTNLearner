package com.psgpw.geek_ttn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.databinding.ActivityTopicDetailBinding

class TopicDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTopicDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}