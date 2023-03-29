package com.example.tablayout.alluser.activity.viewmodal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tablayout.alluser.activity.repository.AllUserRepository
import com.example.tablayout.signup.activity.modalclass.RegisterModal


class AllUserViewModal(val context:Context):ViewModel() {
  private val repository: AllUserRepository by lazy {
    AllUserRepository()
  }



  init {
      repository.getDat(context)
  }

  val live:LiveData<List<RegisterModal>>
  get() = repository.data
  val currenUser:LiveData<List<RegisterModal>>
    get() = repository.currenData

}