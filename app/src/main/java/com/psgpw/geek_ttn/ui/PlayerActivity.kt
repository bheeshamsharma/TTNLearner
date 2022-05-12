package com.psgpw.geek_ttn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarTitle.setText("Video ");
        setSupportActionBar(binding.toolbarGchannel);
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        binding.videoPlayer.setOnPreparedListener {
            it.setOnVideoSizeChangedListener { mp, width, height ->
                val mediaController = MediaController(this)
                binding.videoPlayer.setMediaController(mediaController)
                mediaController.setAnchorView(binding.videoPlayer)
            }
        }
        binding.videoPlayer.setVideoPath("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4")
        binding.videoPlayer.start()
    }
}