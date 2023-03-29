package com.example.tablayout.home.activity.fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tablayout.calls.frament.activity.Calls
import com.example.tablayout.chat.fragment.activity.Chat
import com.example.tablayout.groups.fragment.Groups
import com.example.tablayout.status.fragment.activity.Status

class HomeFragmentAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return totalCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0-> Groups()
            1 -> Chat()
            2 -> Status()
            3-> Calls()

            else -> Chat()
        }
    }
}
