package com.psgpw.geek_ttn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.psgpw.geek_ttn.R
import com.psgpw.geek_ttn.data.dummymodel.Assignment
import com.psgpw.geek_ttn.data.dummymodel.SubTopicRefVideo
import com.psgpw.geek_ttn.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.getBundleExtra("detail")
        val array = bundle?.getParcelableArrayList<SubTopicRefVideo>("videos")
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
        binding.videoPlayer.setOnCompletionListener {
            if (currentPosition >= array?.size!!) {

            } else {
                val next = array?.get(currentPosition++)
                binding.videoPlayer.setVideoPath(next?.ved_link)
                binding.videoPlayer.start()
                //to keep looping into the list, reset the counter to 0.
                //in case you need to stop playing after the list is completed remove the code.
                //to keep looping into the list, reset the counter to 0.
                //in case you need to stop playing after the list is completed remove the code.
                if (currentPosition === array?.size!!) {
                    currentPosition = 0
                }
            }
        }
        binding.videoPlayer.setVideoPath(array?.get(0)?.ved_link)
        binding.videoPlayer.start()

        //  binding.videoPlayer.setVideoPath("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4")
    }
}