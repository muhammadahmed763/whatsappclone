package com.example.tablayout.signup.activity.activity.viewmodal


import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.tablayout.signup.activity.activity.repository.SignupRepository

class SignupViewModal(val context: Context ,val name:String, val email:String, val password:String
                      , val uri: Uri,):ViewModel() {
    private val repository by lazy { SignupRepository() }


    fun post(){
        repository.postData(context, name, email, uri, password)
    }

}