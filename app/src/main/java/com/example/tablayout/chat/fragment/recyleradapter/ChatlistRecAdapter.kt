package com.example.tablayout.chat.fragment.recyleradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tablayout.R
import com.example.tablayout.chat.fragment.modalclass.UserChatModal
import com.example.tablayout.databinding.ChatlistreclayoutBinding

class ChatlistRecAdapter(
    var modallist:ArrayList<UserChatModal>
) :
    RecyclerView.Adapter<ChatlistRecAdapter.ViewHolder>() {
    lateinit var click: BookingRecClick
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =ChatlistreclayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }
    interface BookingRecClick {
        fun clickFunction(position:Int,modal: UserChatModal)
    }
    fun recylerClick(listener: BookingRecClick){
        click=listener
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var modal=modallist[position]
        holder.itemBinding.name.text=modal.name
        holder.itemBinding.lastmessage.text=modal.lastmessage
        holder.itemBinding.time.text=modal.time
        if (modal.checkmessage == "Online"){
            holder.itemBinding.checkMessage.setImageResource(R.drawable.checkmesageblue)
        }else{
            holder.itemBinding.checkMessage.setImageResource(R.drawable.messagecheck)
        }
        Glide.with(holder.itemView.context).load(modal.image).into(holder.itemBinding.userImage)

        holder.itemView.setOnClickListener {
            click.clickFunction(position,modal)
        }
    }
    override fun getItemCount(): Int {
        return modallist.size
    }
    class ViewHolder(var itemBinding:ChatlistreclayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }
}