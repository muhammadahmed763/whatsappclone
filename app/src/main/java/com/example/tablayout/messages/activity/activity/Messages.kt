package com.example.tablayout.messages.activity.activity

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.ParseException
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.tablayout.R
import com.example.tablayout.chat.fragment.modalclass.UserChatModal
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.databinding.ActivityMessagesBinding
import com.example.tablayout.messages.activity.viewmodal.MessengerModal
import com.example.tablayout.messages.activity.recyler.MessengerRecAdapter
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.tools.InformUserOnline
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*
import kotlin.collections.ArrayList


class Messages : AppCompatActivity() {
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    val mediaRecorder by lazy { MediaRecorder() }
    var fabVisible = false
    lateinit var time:String
    val mediaPlayer by lazy { MediaPlayer() }
    val path:String by lazy { Environment.getExternalStorageDirectory().toString()+"/myrec.3gp" }
    private val modal:ArrayList<MessengerModal> by lazy { ArrayList() }
    private val adapter by lazy { MessengerRecAdapter(this,modal) }
    var receiverRoom:String?=null
    var senderRoom:String?=null
    lateinit var getData:MessengerModal
    private val informUserOnline by lazy { InformUserOnline(this,true) }
    lateinit var binding: ActivityMessagesBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        checkUserIsOnline()
        backButton()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        fabVisible= false


        val name=intent.getStringExtra("n")
        val image=intent.getStringExtra("img")
        val senderUid=SessionManager.getToken(this)
        val receiveruid=intent.getStringExtra("id")
        senderRoom=receiveruid+senderUid
        binding.userName.setText(name)
        Glide.with(this).load(image).into(binding.userImage)

        // userOnline
        RealtimeDatabase.realtime.child(receiveruid!!).child("online").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val isOnline = snapshot.getValue(Boolean::class.java) ?: false
                if (isOnline) {
                    binding.checkUserOnline.setText("Online")
                    RealtimeDatabase.realtime.child(Variabls.chat).child(receiveruid!!).child("checkmessage")
                        .setValue("Online")
                    RealtimeDatabase.realtime.child(Variabls.chat).child(receiveruid!!).child("UserOnlineType")
                        .setValue("Online")
                } else {
                    binding.checkUserOnline.setText("Offline")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Listener was cancelled")
            }
        })
        // userOnline


        RealtimeDatabase.realtime.child(Variabls.messagefolder)
            .child(senderRoom!!).child(Variabls.message).addValueEventListener(
                object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        modal.clear()
                        for (data in snapshot.children){
                            getData= data.getValue(MessengerModal::class.java)!!
                            modal.add(getData)
                            adapter.notifyDataSetChanged()
                        }
                        adapter.notifyDataSetChanged()
                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                }
            )

        recylerView()
        sendMessages()
    }

    private fun backButton() {
        binding.backBtn.setOnClickListener{
            Toast.makeText(this, "dek", Toast.LENGTH_SHORT).show()
            val informUserOnline by lazy { InformUserOnline(this,false) }
            informUserOnline.checkUser()
        }
    }

    private fun checkUserIsOnline() {
        informUserOnline.checkUser()
    }




     fun recylerView() {
        adapter.notifyDataSetChanged()
        binding.recylerView.adapter=adapter
         //seenMessage
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun sendMessages() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val ampm = if(c.get(Calendar.AM_PM)==0) "AM " else "PM "
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val key= SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        try {
            val sdf = SimpleDateFormat("H:mm")
            val dateObj = sdf.parse(currentTime)
            time="${SimpleDateFormat("K:mm").format(dateObj)}\t${ampm}"

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val senderUid=SessionManager.getToken(this)
        val receiveruid=intent.getStringExtra("id")
        senderRoom=receiveruid+senderUid
        receiverRoom=senderUid+receiveruid
        binding.voiceRecord.setOnClickListener {
            val message=binding.messageBox.text.toString()
            val messageObject= MessengerModal(message,senderUid, year.toString(),month.toString()
            ,day.toString(),time,false,receiveruid,receiverRoom,senderRoom)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            RealtimeDatabase.realtime.child(Variabls.messagefolder)
                .child(senderRoom!!).child(Variabls.message)
                .push().setValue(messageObject).addOnSuccessListener {
                    RealtimeDatabase.realtime.child(Variabls.messagefolder)
                        .child(receiverRoom!!).child(Variabls.message)
                        .push().setValue(messageObject)
                    adapter.notifyDataSetChanged()
                }

            binding.messageBox.setText("")
            RealtimeDatabase.realtime.child(Variabls.chat).child(receiveruid!!).child("time")
                .setValue(time)
            RealtimeDatabase.realtime.child(Variabls.chat).child(receiveruid!!).child("lastmessage")
                .setValue(message)
        }


    }




    override fun onBackPressed() {
        super.onBackPressed()
        val informUserOnline by lazy { InformUserOnline(this,false) }
        informUserOnline.checkUser()
    }
}