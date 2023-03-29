package com.example.tablayout.alluser.activity.recylerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.tablayout.databinding.UserreclayoutBinding
import com.example.tablayout.signup.activity.modalclass.RegisterModal

class AllUserRecAdapter(
    var modallist:ArrayList<RegisterModal>
) :
    RecyclerView.Adapter<AllUserRecAdapter.ViewHolder>() {
    lateinit var click: BookingRecClick
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =UserreclayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }
    interface BookingRecClick {
        fun clickFunction(position:Int,modal:RegisterModal)
    }
    fun recylerClick(listener: BookingRecClick){
        click=listener
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var modal=modallist[position]
        holder.itemBinding.name.text=modal.userName
        Glide.with(holder.itemView.context).load(modal.image).into(holder.itemBinding.userImage)
        holder.itemBinding.userImage.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Okk", Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnClickListener {
            click.clickFunction(position,modal)
        }
    }
    override fun getItemCount(): Int {
        return modallist.size
    }
    class ViewHolder(var itemBinding:UserreclayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }
}