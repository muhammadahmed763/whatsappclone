package com.example.tablayout.status.fragment.viewmodalfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.status.fragment.viewmodal.StatusViewModal

class StatusFactory(val context:Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatusViewModal(context) as T
    }
}