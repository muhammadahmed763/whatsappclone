package com.example.tablayout.login.activity.activity

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tablayout.OnlineUser
import com.example.tablayout.utils.urils.Variabls
import com.example.tablayout.databinding.ActivityMainBinding
import com.example.tablayout.home.activity.Home
import com.example.tablayout.sharedpref.SessionManager
import com.example.tablayout.signup.activity.activity.activity.Signup
import com.example.tablayout.signup.activity.activity.viewmodal.SignupViewModal
import com.example.tablayout.signup.activity.activity.viewmodalfactory.SignupViewModalFactory
import com.example.tablayout.signup.activity.viewmodal.LoginViewModal
import com.example.tablayout.signup.activity.viewmodalfactory.LoginFactory
import com.example.whatsappclone.firebase.auth.Authentication
import com.example.whatsappclone.firebase.firebasedatabase.RealtimeDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var viewmodal: LoginViewModal
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        checkUser()
        loginUser()
        goSignupActivity()

    }



    private fun goSignupActivity() {
        binding.goSignUp.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }
    }
    private fun checkUser() {
        if (SessionManager.getToken(this)!=null){
            startActivity(Intent(this, Home::class.java))
        }else{

        }
    }

    private fun loginUser() {
        binding.loginBtn.setOnClickListener {
            val email=binding.emailEd.text.toString()
            val password=binding.passwordEd.text.toString()
            viewmodal= ViewModelProvider(this, LoginFactory( this,email, password))
                .get(LoginViewModal::class.java)
            viewmodal.login()


            startActivity(Intent(this,Home::class.java))
        }
    }
}