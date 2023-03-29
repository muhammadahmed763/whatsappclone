package com.example.tablayout.status.fragment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.status.fragment.modalclass.UploadStatusModal
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class StatusRepositoty {
    private val liveData = MutableLiveData<List<UploadStatusModal>>()
    fun getDat(context: Context) {
        RealtimeDatabase.realtime.child(Variabls.status).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val dataList = mutableListOf<UploadStatusModal>()
                    for (childSnapshot in dataSnapshot.children) {
                        val data = childSnapshot.getValue(UploadStatusModal::class.java)
                        if (SessionManager.getToken(context)==data!!.uid){
                            dataList.add(data!!)
                        }
                    }
                    liveData.value = dataList
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

    fun statusTimer(context: Context){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        RealtimeDatabase.realtime.child(Variabls.status)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(d in snapshot.children){
                        val list= mutableListOf<UploadStatusModal>()
                        val da=d.getValue(UploadStatusModal::class.java)
                        list.add(da!!)
                        for (m in list){
                            if (m.day == day.toInt()-1) {
                                RealtimeDatabase.realtime.child(Variabls.status)
                                    .child(SessionManager.getToken(context).toString())
                                    .removeValue()
                            }
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    val data:LiveData<List<UploadStatusModal>>
        get()=liveData
}