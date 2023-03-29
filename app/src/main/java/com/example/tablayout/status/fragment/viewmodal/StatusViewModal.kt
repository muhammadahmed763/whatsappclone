package com.example.tablayout.status.fragment.viewmodal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tablayout.chat.fragment.modalclass.UserChatModal
import com.example.tablayout.chat.fragment.repository.ChatlistRepository
import com.example.tablayout.status.fragment.modalclass.UploadStatusModal
import com.example.tablayout.status.fragment.repository.StatusRepositoty

class StatusViewModal(val context: Context):ViewModel()
{
    private val repository: StatusRepositoty by lazy {
        StatusRepositoty()
    }


    init {
        repository.getDat(context)
        repository.statusTimer(context)
    }

    val live:LiveData<List<UploadStatusModal>>
        get() = repository.data
}