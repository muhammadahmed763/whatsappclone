package com.example.tablayout.messages.activity.messengermvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tablayout.messages.activity.viewmodal.MessengerModal


class MessengerViewModal(val token:String):ViewModel() {

  private val dataList = mutableListOf<MessengerModal>()
  private val mutableLiveData:MutableLiveData<List<MessengerModal>> = MutableLiveData()

  init {

  }

  val liveData:MutableLiveData<List<MessengerModal>>
  get() = mutableLiveData
}