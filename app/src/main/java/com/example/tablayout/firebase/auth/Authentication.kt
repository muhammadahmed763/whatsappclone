package com.example.whatsappclone.firebase.auth

import com.google.firebase.auth.FirebaseAuth

object Authentication {
    val auth:com.google.firebase.auth.FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
}