package com.psgpw.geek_ttn.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.SubTopic
import com.psgpw.geek_ttn.databinding.ActivityTopicDetailBinding

class TopicDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopicDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("detail")
        val topic = bundle?.getParcelable<SubTopic>("subTopic")
        binding.toolbarTitle.setText(topic?.stopic_name);
        setSupportActionBar(binding.toolbarGchannel);
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        setData(topic)

        binding.btnWatchVideo.setOnClickListener {
            if (topic?.refvideos != null && topic.refvideos.isNotEmpty()) {
                val bundle = bundleOf("videos" to topic?.refvideos)
                val intent = Intent(
                    this@TopicDetailActivity,
                    PlayerActivity::class.java
                )
                intent.putExtra("detail", bundle)
                startActivity(
                    intent
                )
            } else {
                Toast.makeText(this, "Sorry ,No video found for this Topic.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnSubmitAssignment.setOnClickListener {
            val bundle = bundleOf("type" to "assignment")
            val intent = Intent(
                this@TopicDetailActivity,
                MainActivity::class.java
            )
            intent.putExtra("open", bundle)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(
                intent
            )

            finish()
        }

        binding.btnChatExpert.setOnClickListener {
            val bundle = bundleOf("type" to "chat")
            val intent = Intent(
                this@TopicDetailActivity,
                MainActivity::class.java
            )
            intent.putExtra("open", bundle)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(
                intent
            )

            finish()
        }

    }

    private fun setData(topic: SubTopic?) {
        binding.tvDesc.setText(topic?.description)
        if (topic?.reflink != null && topic.reflink.isNotEmpty()) {
            binding.tvRefLink1.setText(topic.reflink.get(0).ref_link)
        }
    }

}