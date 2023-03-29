package com.example.tablayout.chat.fragment.viewmodal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tablayout.chat.fragment.modalclass.UserChatModal
import com.example.tablayout.chat.fragment.repository.ChatlistRepository
import com.example.tablayout.signup.activity.modalclass.RegisterModal


class ChatlistViewModal(val context:Context):ViewModel() {
  private val repository: ChatlistRepository by lazy {
    ChatlistRepository()
  }


  init {
      repository.getDat(context)
  }

  val live:LiveData<List<UserChatModal>>
  get() = repository.data
}