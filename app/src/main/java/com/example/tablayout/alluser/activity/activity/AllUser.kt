package com.example.tablayout.alluser.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.alluser.activity.recylerview.AllUserRecAdapter
import com.example.tablayout.alluser.activity.viewmodal.AllUserViewModal
import com.example.tablayout.alluser.activity.viewmodalfactory.AllUserFactory


import com.example.tablayout.chat.fragment.modalclass.UserChatModal
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.databinding.ActivityAllUserBinding
import com.example.tablayout.home.activity.Home
import com.example.tablayout.signup.activity.modalclass.RegisterModal
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase

class AllUser : AppCompatActivity() {


    lateinit var binding: ActivityAllUserBinding
    val adapter: AllUserRecAdapter by lazy { AllUserRecAdapter(modal) }
    val modal: ArrayList<RegisterModal> by lazy { ArrayList() }
    lateinit var viewModel:AllUserViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModal()
        recylerView()

    }

    private fun recylerView() {
        viewModel=ViewModelProvider(this, AllUserFactory(this)).get(AllUserViewModal::class.java)
        viewModel.live.observe(this){data->
            modal.addAll(data)
            adapter.notifyDataSetChanged()
        }
        binding.recylerview.adapter = adapter
        adapter.notifyDataSetChanged()
        adapter.recylerClick(object :AllUserRecAdapter.BookingRecClick{
            override fun clickFunction(position: Int, modal: RegisterModal) {
                modal.uid?.let {
                    RealtimeDatabase.realtime.child(Variabls.chat).child(modal.uid.toString())
                        .setValue(
                            UserChatModal(modal.userName,modal.email,
                            modal.image,modal.uid,"","","","")
                        )
                }
                startActivity(Intent(this@AllUser,Home::class.java))
            }
        })
    }
    private fun viewModal() {

    }
}