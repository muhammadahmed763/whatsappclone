package com.example.tablayout.utils.urils

import android.app.ProgressDialog
import android.net.Uri
import com.example.tablayout.alluser.activity.viewmodal.AllUserViewModal
import com.example.tablayout.firebase.storageupload.FirebaseStorageUpload
import com.example.tablayout.status.fragment.viewmodal.StatusViewModal

object Variabls {
    const val onlineUser:String="Online"
    const val offlineUser:String="Offline"
    const val user:String="UserContacts"
    const val chat:String="ChatUser"
    const val message:String="Messages"
    const val messagefolder:String="MessageFolder"
    const val status:String="Status"
    const val imageUpload:String="images.jpg"
    const val sendUri:String="Uri"
    const val sendUid:String="Uid"
    const val sendName:String="Name"
    const val sendImage:String="Image"
    var imageUri: Uri?=null
    lateinit var email:String
    lateinit var name:String
    lateinit var password:String
    val pickImage = 100
    lateinit var progressDialog: ProgressDialog
    var REQUEST_CODE=111
    lateinit var allUserViewModal: AllUserViewModal
    lateinit var firebaseStorageUpload: FirebaseStorageUpload
    lateinit var statusViewModal: StatusViewModal
}