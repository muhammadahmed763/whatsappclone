package com.example.tablayout.signup.activity.activity.repository

import android.content.Context
import android.net.Uri
import com.example.tablayout.tools.ProgressDialogueUtil
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.signup.activity.modalclass.RegisterModal
import com.example.whatsappclone.firebase.auth.Authentication
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.example.whatsappclone.firebase.firebasestorage.FirebaseStorageObj

class SignupRepository {

    fun postData(context:Context, name:String, email:String,uri: Uri,password:String){
        val dialogueUtil by lazy { ProgressDialogueUtil(context) }
        dialogueUtil.show("Creating User")
        Authentication.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            FirebaseStorageObj.storage.reference.child("User")
                .child(Authentication.auth.currentUser!!.uid).putFile(uri)
                .addOnSuccessListener {
                    FirebaseStorageObj.storage.reference.child("User")
                        .child(Authentication.auth.currentUser!!.uid).downloadUrl
                        .addOnSuccessListener {ur->
                            SessionManager.saveAuthToken(context,Authentication.auth.currentUser!!.uid)
                            RealtimeDatabase.realtime.child("MyUsers").child(
                                Authentication.auth.currentUser!!.uid
                            ).setValue(RegisterModal(ur.toString(),name,email,Authentication.auth.currentUser!!.uid))
                                .addOnSuccessListener {
                                    dialogueUtil.hide()
                                }.addOnFailureListener{
                                    dialogueUtil.hide()
                                }
                        }
                }.addOnFailureListener{
                    dialogueUtil.hide()
                }
        }
    }
}