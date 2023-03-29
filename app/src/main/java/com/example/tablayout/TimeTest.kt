package com.example.tablayout

import android.content.ContentValues.TAG
import android.icu.text.SimpleDateFormat
import android.net.ParseException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.tablayout.databinding.ActivityTimeTestBinding
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.signup.activity.modalclass.RegisterModal
import com.example.tablayout.utils.urils.Variabls
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class TimeTest : AppCompatActivity() {
    lateinit var binding:ActivityTimeTestBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTimeTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        onlneUSer()
//            val ampm = if(c.get(Calendar.AM_PM)==0) "AM " else "PM "
//            val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
//                try {
//                    val sdf = SimpleDateFormat("H:mm")
//                    val dateObj = sdf.parse(currentTime)
//                binding.textView.setText("${SimpleDateFormat("K:mm").format(dateObj)}\t${ampm}")
//
//                } catch (e: ParseException) {
//                    e.printStackTrace()
//                }
//            }else{
//                binding.textView.setText("${currentTime}")
//            }
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
    }

    private fun onlneUSer() {
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
                    presenceRef.setValue(true)
                    presenceRef.onDisconnect().setValue(false)
                } else {
                    // User is disconnected
                    presenceRef.setValue(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Listener was cancelled")
            }
        })


        val uidToCheck = "grFSA0mgCZhhEJUJF8NTp0XOzXK2"
        usersRef.child(uidToCheck).child("online").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val isOnline = snapshot.getValue(Boolean::class.java) ?: false
                if (isOnline) {
                    // User is online
                   binding.textView.setText("Online")
                } else {
                    // User is offline
                    binding.textView.setText("Offline")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Listener was cancelled")
            }
        })

    }

}