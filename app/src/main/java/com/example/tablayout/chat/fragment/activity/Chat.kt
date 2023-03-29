package com.example.tablayout.chat.fragment.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.alluser.activity.activity.AllUser
import com.example.tablayout.chat.fragment.viewmodalfactory.ChatlistFactory
import com.example.tablayout.chat.fragment.recyleradapter.ChatlistRecAdapter
import com.example.tablayout.chat.fragment.viewmodal.ChatlistViewModal
import com.example.tablayout.chat.fragment.modalclass.UserChatModal
import com.example.tablayout.databinding.FragmentChatBinding
import com.example.tablayout.home.activity.callback.CallBack
import com.example.tablayout.messages.activity.activity.Messages
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.signup.activity.modalclass.RegisterModal
import com.example.tablayout.tools.InformUserHalfOnline
import com.example.tablayout.tools.InformUserOnline
import com.example.tablayout.utils.urils.Variabls
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase

class Chat : Fragment() {
    lateinit var binding: FragmentChatBinding
    lateinit var viewModel: ChatlistViewModal
    lateinit var callBack: CallBack
    val adapter: ChatlistRecAdapter by lazy { ChatlistRecAdapter(modal) }
    val modal: ArrayList<UserChatModal> by lazy { ArrayList() }
    private val informUserOnline by lazy { InformUserOnline(requireContext(),true) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentChatBinding.inflate(inflater, container, false)
        val v=binding.root


        informUserOnlin()
        recylerView()
        viewModal()
        addUser()
        return v
    }

    private fun informUserOnlin() {
        informUserOnline.checkUser()
    }
    private fun calltoActivity() {
        callBack=context as CallBack
        callBack.call(Chat())
    }

    private fun recylerView() {
        binding.recylerview.adapter = adapter
        adapter.notifyDataSetChanged()
        adapter.recylerClick(object : ChatlistRecAdapter.BookingRecClick{
            override fun clickFunction(position: Int, modal: UserChatModal) {
                val intent=Intent(requireContext(), Messages::class.java)
                intent.putExtra("id",modal.uid)
                intent.putExtra("img",modal.image)
                intent.putExtra("n",modal.name)
                startActivity(intent)
            }
        })
    }
    private fun viewModal(){
        viewModel= ViewModelProvider(requireActivity(), ChatlistFactory(requireContext())).get(
            ChatlistViewModal::class.java)
        viewModel.live.observe(requireActivity()){data->
            modal.clear()
            modal.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }
    private fun addUser() {
        binding.addBtn.setOnClickListener {
            startActivity(Intent(requireContext(), AllUser::class.java))
        }
    }


}