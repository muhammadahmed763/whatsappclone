package com.example.tablayout.status.fragment.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tablayout.alluser.activity.viewmodal.AllUserViewModal
import com.example.tablayout.alluser.activity.viewmodalfactory.AllUserFactory
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.databinding.FragmentStatusBinding
import com.example.tablayout.firebase.storageupload.FirebaseStorageUpload
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.status.fragment.viewmodal.StatusViewModal
import com.example.tablayout.status.fragment.viewmodalfactory.StatusFactory
import com.example.tablayout.statusview.activity.VIewStatus
import com.example.tablayout.tools.InformUserOnline
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import java.util.*


class Status : Fragment() {
    lateinit var binding: FragmentStatusBinding
    val PICK_IMAGE = 1 // request code for selecting an image
    val PICK_VIDEO = 2
    var uri:Uri?=null
    lateinit var viewModal: StatusViewModal
    private val informUserOnline by lazy { InformUserOnline(requireContext(),true) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentStatusBinding.inflate(inflater,container,false)

        val v=binding.root

        getCurrentUser()
        selectStatus()
        uploadStatus()
        informUserOnlin()
        return v
    }

    private fun informUserOnlin() {
        informUserOnline.checkUser()
    }
    private fun getCurrentUser() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        Variabls.allUserViewModal=ViewModelProvider(requireActivity(),AllUserFactory(requireContext()))[AllUserViewModal::class.java]
        Variabls.allUserViewModal.currenUser.observe(requireActivity()){ dat->
            for (d in dat){
                Glide.with(requireActivity()).load(d.image).into(binding.currentUserImage)
            }
        }
        viewModal=ViewModelProvider(requireActivity(),StatusFactory(requireContext()))[StatusViewModal::class.java]
        viewModal.live.observe(requireActivity()){data->
            for (d in data){
                binding.card.setOnClickListener {
                    val intent=Intent(requireContext(), VIewStatus::class.java)
                    intent.putExtra(Variabls.sendUri,d.status)
                    startActivity(intent)
                }
            }
        }
    }
    private fun uploadStatus() {
        val upload by lazy { FirebaseStorageUpload(requireContext(),uri!!) }
        binding.addstatusText.setOnClickListener {
            upload.upload()
        }
    }
    private fun selectStatus() {
        binding.addStatusBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/* video/*"
            startActivityForResult(Intent.createChooser(intent, "Select file"), PICK_IMAGE)

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE -> {

                      uri= data?.data
                    RealtimeDatabase.realtime.child("Image").setValue("Image")
                }
                PICK_VIDEO -> {
                    uri = data?.data
                }
            }
        }
    }
}