package com.example.tablayout.firebase.storageupload

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.status.fragment.modalclass.UploadStatusModal
import com.example.whatsappclone.firebase.auth.Authentication
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.example.whatsappclone.firebase.firebasestorage.FirebaseStorageObj
import java.util.*

class FirebaseStorageUpload(val context: Context,val uri:Uri) {

    fun upload(){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        FirebaseStorageObj.storage.reference.child(Variabls.status).child(Authentication.auth.currentUser!!.uid).putFile(uri)
            .addOnSuccessListener {
                FirebaseStorageObj.storage.reference.child(Variabls.status).child(Authentication.auth.currentUser!!.uid)
                    .downloadUrl.addOnSuccessListener {uri->
                        RealtimeDatabase.realtime.child(Variabls.status)
                            .child(Authentication.auth.currentUser!!.uid).setValue(
                                UploadStatusModal(uri.toString(),SessionManager.getToken(context),day)
                            ).addOnSuccessListener {
                            }
                    }
            }
    }
}