package com.example.tablayout.signup.activity.viewmodal

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tablayout.signup.activity.modalclass.RegisterModal
import com.example.tablayout.signup.activity.repository.LoginRepository
import com.example.whatsappclone.firebase.error_handling_response.BaseResponse
class LoginViewModal(val context: Context,val email:String, val password:String):ViewModel()
{

    private val repository:LoginRepository by lazy { LoginRepository() }

    fun login(){
        repository.register(context, email, password)
    }

}