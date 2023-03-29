package com.example.tablayout.chat.fragment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.tablayout.chat.fragment.modalclass.UserChatModal
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.signup.activity.modalclass.RegisterModal
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChatlistRepository {
    private val liveData = MutableLiveData<List<UserChatModal>>()
    fun getDat(context: Context) {
        RealtimeDatabase.realtime.child(Variabls.chat).addValueEventListener(
            object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val dataList = mutableListOf<UserChatModal>()
                    for (childSnapshot in snapshot.children) {
                        val data = childSnapshot.getValue(UserChatModal::class.java)
                        if (SessionManager.getToken(context)!=data!!.uid){
                            dataList.add(data!!)
                        }
                    }
                    liveData.value = dataList
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
    val data:LiveData<List<UserChatModal>>
        get()=liveData
}