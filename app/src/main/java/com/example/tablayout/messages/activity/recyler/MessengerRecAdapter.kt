package com.example.tablayout.messages.activity.recyler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tablayout.databinding.ReceiverreclayoutBinding
import com.example.tablayout.databinding.SenderreclayoutBinding
import com.example.tablayout.messages.activity.viewmodal.MessengerModal
import com.example.tablayout.sharedpref.SessionManager

class MessengerRecAdapter(val context: Context,var modallist:ArrayList<MessengerModal>
) :
    RecyclerView.Adapter<ViewHolder>() {
    val ITEMRECEIVE = 1
    val ITEMSENT = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==1){
            val receiveView=ReceiverreclayoutBinding.inflate(LayoutInflater.from(context)
                ,parent,false)
            return ReceiveViewHolder(receiveView)
        }else{
            val sentView=SenderreclayoutBinding.inflate(LayoutInflater.from(context)
                ,parent,false)
            return SentViewHolder(sentView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage=modallist[position]
        if (holder.javaClass==SentViewHolder::class.java){
            val viewHolder=holder as SentViewHolder
            holder.senderBinding.senderMessage.text=currentMessage.message
            holder.senderBinding.time.text=currentMessage.currentTime
        }else{
            val viewHolder=holder as ReceiveViewHolder
            holder.receiverBinding.recevermessage.text=currentMessage.message
            holder.receiverBinding.time.text=currentMessage.currentTime
        }
    }
    override fun getItemCount(): Int {
        return modallist.size
    }
    class SentViewHolder(var senderBinding:SenderreclayoutBinding) :
        ViewHolder(senderBinding.root) {
    }
    class ReceiveViewHolder(var receiverBinding:ReceiverreclayoutBinding) :
        ViewHolder(receiverBinding.root) {
    }
    override fun getItemViewType(position: Int): Int {
        val currentMessage=modallist[position]
        if (SessionManager.getToken(context)==currentMessage.senderId){
            return ITEMSENT
        }else{
            return ITEMRECEIVE
        }
    }
}