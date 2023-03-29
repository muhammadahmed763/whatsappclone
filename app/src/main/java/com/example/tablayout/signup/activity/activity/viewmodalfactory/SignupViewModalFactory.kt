package com.example.tablayout.signup.activity.activity.viewmodalfactory

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.signup.activity.activity.viewmodal.SignupViewModal

class SignupViewModalFactory(val context: Context, val name:String, val email:String, val password:String
                             , val uri: Uri,):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignupViewModal(context, name, email, password, uri)as T
    }
}