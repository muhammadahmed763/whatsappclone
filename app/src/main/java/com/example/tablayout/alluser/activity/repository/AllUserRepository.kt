package com.example.tablayout.alluser.activity.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.signup.activity.modalclass.RegisterModal
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AllUserRepository {
    private val liveData = MutableLiveData<List<RegisterModal>>()
    private val currenUser = MutableLiveData<List<RegisterModal>>()
    fun getDat(context: Context) {
        RealtimeDatabase.realtime.child(Variabls.user).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val dataList = mutableListOf<RegisterModal>()
                    val currentUse = mutableListOf<RegisterModal>()
                    for (childSnapshot in dataSnapshot.children) {
                        val data = childSnapshot.getValue(RegisterModal::class.java)
                        if (SessionManager.getToken(context)!=data!!.uid){
                            dataList.add(data!!)
                        }

                        if (SessionManager.getToken(context)==data!!.uid){
                            currentUse.add(data!!)
                        }

                    }
                    liveData.value = dataList
                    currenUser.value = currentUse

                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

    }
    val data:LiveData<List<RegisterModal>>
        get()=liveData
    val currenData:LiveData<List<RegisterModal>>
        get()=currenUser

}