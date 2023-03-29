package com.example.tablayout.statusview.activity

import android.net.Uri
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.databinding.ActivityViewStatusBinding

class VIewStatus : AppCompatActivity() {
    lateinit var binding:ActivityViewStatusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityViewStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val getstatus=intent.getStringExtra(Variabls.sendUri)
//        Glide.with(this).load(getstatus).into(binding.videoView)
        playStatus()
    }

    private fun playStatus() {
        val getstatus=intent.getStringExtra(Variabls.sendUri)
        val uri:Uri=Uri.parse(getstatus)

        binding.videoView.setVideoURI(uri)

        // on below line we are creating variable
        // for media controller and initializing it.
        val mediaController = MediaController(this)

        // on below line we are setting anchor
        // view for our media controller.
        mediaController.setAnchorView(binding.videoView)

        // on below line we are setting media player
        // for our media controller.
        mediaController.setMediaPlayer(binding.videoView)

        // on below line we are setting media
        // controller for our video view.
        binding.videoView.setMediaController(mediaController)

        // on below line we are
        // simply starting our video view.
        binding.videoView.start()
    }
}