package com.example.tablayout.alluser.activity.viewmodalfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.alluser.activity.viewmodal.AllUserViewModal

class AllUserFactory(val context:Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllUserViewModal(context) as T
    }
}