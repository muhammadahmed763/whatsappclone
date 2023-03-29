package com.example.whatsappclone.firebase.firebasedatabase

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealtimeDatabase {
    val realtime by lazy {
        Firebase.database.reference
    }
}