package com.example.tablayout.roomdatabase

import android.content.Context
import androidx.room.Room



object DatabaseClient {
    lateinit var database: AppDatabase
        private set
    fun init(context: Context) {
        database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "my_database"
        ).build()
    }
}
