package com.example.tablayout.messages.activity.messengermvvm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MessengerFactory(val token:String):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MessengerViewModal(token) as T
    }
}