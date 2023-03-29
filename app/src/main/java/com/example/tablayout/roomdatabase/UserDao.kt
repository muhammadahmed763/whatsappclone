package com.example.tablayout.roomdatabase

import androidx.room.*

@Dao
interface UserDao {
    @Insert
     fun insertUser(user: User)

    @Update
     fun updateUser(user: User)

    @Delete
     fun deleteUser(user: User)

    @Query("SELECT * FROM users")
     fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE path = :email")
     fun getUserByEmail(email: String): User
}