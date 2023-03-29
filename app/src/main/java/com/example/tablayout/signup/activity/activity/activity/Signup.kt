package com.example.tablayout.signup.activity.activity.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.databinding.ActivitySignupBinding

import com.example.tablayout.home.activity.Home
import com.example.tablayout.signup.activity.activity.viewmodal.SignupViewModal
import com.example.tablayout.signup.activity.activity.viewmodalfactory.SignupViewModalFactory

class Signup : AppCompatActivity() {
    lateinit var viewmodal:SignupViewModal
     var imageUri:Uri?=null
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)



        callViewModal()
        imagePick()
    }

    private fun callViewModal() {
        binding.registerBtn.setOnClickListener {
            val name=binding.nameEd.text.toString()
            val email=binding.emailEd.text.toString()
            val password=binding.passwordEd.text.toString()
            viewmodal=ViewModelProvider(this,SignupViewModalFactory( this,
                name,
                email,
                password,imageUri!!)).get(SignupViewModal::class.java)
            viewmodal.post()
            startActivity(Intent(this,Home::class.java))
        }
    }


    private fun imagePick() {
        binding.image.setOnClickListener {
            launchGallery()
        }
    }
    private fun launchGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, Variabls.pickImage)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == Variabls.pickImage) {
            imageUri = data!!.data!!
            binding.image.setImageURI(imageUri)
        }
    }
}