package com.example.tablayout.chat.fragment.viewmodalfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.chat.fragment.viewmodal.ChatlistViewModal

class ChatlistFactory(val context:Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatlistViewModal(context) as T
    }
}