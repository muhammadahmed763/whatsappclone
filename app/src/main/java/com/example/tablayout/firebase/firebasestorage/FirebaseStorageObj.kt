package com.example.whatsappclone.firebase.firebasestorage

object FirebaseStorageObj {
    val storage:com.google.firebase.storage.FirebaseStorage by lazy {
        com.google.firebase.storage.FirebaseStorage.getInstance()
    }
}