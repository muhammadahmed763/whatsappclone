package com.example.tablayout.tools

import android.content.Context
import com.example.tablayout.sharedpref.SessionManager
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class InformUserHalfOnline(val context: Context,val user:Boolean) {
    fun checkUser(){
        val connectedRef = Firebase.database.getReference(".info/connected")
        val presenceRef = RealtimeDatabase.realtime.child(SessionManager.getToken(context).toString()).child("online")
        connectedRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val connected = snapshot.getValue(Boolean::class.java) ?: false
                if (connected) {
                    presenceRef.setValue(user)
                    presenceRef.onDisconnect().setValue(false)
                } else {
                    presenceRef.setValue(false)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}