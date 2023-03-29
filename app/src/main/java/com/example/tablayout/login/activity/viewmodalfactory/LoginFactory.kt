package com.example.tablayout.signup.activity.viewmodalfactory

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.signup.activity.viewmodal.LoginViewModal


class LoginFactory(val context: Context,val email:String, val password:String):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModal(context,email,password) as T
    }

}