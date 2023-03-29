package com.example.tablayout.home.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tablayout.R
import com.example.tablayout.TimeTest
import com.example.tablayout.chat.fragment.activity.Chat
import com.example.tablayout.databinding.ActivityHomeBinding
import com.example.tablayout.home.activity.callback.CallBack
import com.example.tablayout.home.activity.fragment.adapter.HomeFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator


class Home : AppCompatActivity(),CallBack {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupTabLayout()
        binding.cameraBtn.setOnClickListener {
            startActivity(Intent(this,TimeTest::class.java))
        }

    }

    private fun setupTabLayout() {
        val name= arrayOf("","Chat","Status","Calls")
        TabLayoutMediator(
            binding.tablayout, binding.viewpager
        ) { tab, position -> tab.text =name[position] }.attach()
        binding.tablayout.getTabAt(0)?.setIcon(R.drawable.camera)
        val secondTab = binding.tablayout.getTabAt(1)
        binding.tablayout.selectTab(secondTab)
    }

    private fun setupViewPager() {
        val adapter = HomeFragmentAdapter(this, 4)
        binding.viewpager.adapter = adapter
    }

    override fun onBackPressed() {
        val viewPager = binding.viewpager
        if (viewPager.currentItem == 1) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    override fun call(call: Fragment) {
        when(call){
            is Chat->{
                finishActivity(1)
            }
        }
    }
}
