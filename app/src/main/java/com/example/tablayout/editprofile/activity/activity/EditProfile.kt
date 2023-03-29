package com.example.tablayout.editprofile.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablayout.databinding.ActivityEditProfileBinding

class EditProfile : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}