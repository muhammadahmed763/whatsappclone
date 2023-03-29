package com.example.tablayout.signup.activity.repository

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.tablayout.OnlineUser
import com.example.tablayout.home.activity.Home
import com.example.tablayout.tools.ProgressDialogueUtil
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.signup.activity.modalclass.RegisterModal
import com.example.tablayout.utils.urils.Variabls
import com.example.whatsappclone.firebase.auth.Authentication
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginRepository {
    fun register(context: Context,email:String, password:String){
        val dialog by lazy { ProgressDialogueUtil(context) }
        dialog.show("Longing User")
        Authentication.auth.signInWithEmailAndPassword(
            email, password
        ).addOnSuccessListener {
            val database = Firebase.database
            val usersRef = database.getReference("users")

            val connectedRef = Firebase.database.getReference(".info/connected")

// Get the current user's ID
            val uid = FirebaseAuth.getInstance().currentUser?.uid

// Set up a presence system for the user
            val presenceRef = usersRef.child(uid!!).child("online")
            connectedRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val connected = snapshot.getValue(Boolean::class.java) ?: false
                    if (connected) {
                        // User is connected
                        SessionManager.saveAuthToken(context,Authentication.auth.currentUser!!.uid)
                        presenceRef.setValue(true)
                        presenceRef.onDisconnect().setValue(false)
                        dialog.hide()
                    } else {
                        // User is disconnected
                        SessionManager.saveAuthToken(context,Authentication.auth.currentUser!!.uid)
                        dialog.hide()
                        presenceRef.setValue(false)

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    SessionManager.saveAuthToken(context,Authentication.auth.currentUser!!.uid)
                    dialog.hide()
                }
            })
            SessionManager.saveAuthToken(context,Authentication.auth.currentUser!!.uid)
        }.addOnFailureListener {
            dialog.hide()
        }
    }


}