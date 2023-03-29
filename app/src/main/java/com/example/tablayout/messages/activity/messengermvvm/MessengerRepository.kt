package com.example.tablayout.messages.activity.messengermvvm

import androidx.lifecycle.MutableLiveData

import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.messages.activity.viewmodal.MessengerModal
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MessengerRepository {
    private val liveData = MutableLiveData<List<MessengerModal>>()
    fun getDat(token:String) {
        RealtimeDatabase.realtime.child(Variabls.messagefolder)
            .child(token).child(Variabls.message).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val dataList = mutableListOf<MessengerModal>()
                    for (childSnapshot in dataSnapshot.children) {
                        val data=childSnapshot.getValue(MessengerModal::class.java)
                        dataList.add(data!!)
                    }
                    liveData.value = dataList
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
    val data:MutableLiveData<List<MessengerModal>>
        get()= liveData
}