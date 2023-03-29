package com.example.tablayout.calls.frament.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tablayout.R
import com.example.tablayout.sharedpref.SessionManager


class Calls : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Toast.makeText(requireContext(), "${SessionManager.getToken(requireContext())}", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_calls, container, false)
    }
}