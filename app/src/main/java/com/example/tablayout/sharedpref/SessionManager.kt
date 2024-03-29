package com.example.tablayout.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.example.tablayout.R

object SessionManager {

    const val USER_TOKEN = "user_token"
    const val S = "s"

    fun saveT(context: Context, token: String) {
        saveString(context, S, token)
    }
    fun getT(context: Context): String? {
        return getString(context, S)
    }
    fun saveAuthToken(context: Context, token: String) {
        saveString(context, USER_TOKEN, token)
    }
    fun getToken(context: Context): String? {
        return getString(context, USER_TOKEN)
    }

    fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }

    fun getString(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(USER_TOKEN, null)
    }
    fun clearData(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}