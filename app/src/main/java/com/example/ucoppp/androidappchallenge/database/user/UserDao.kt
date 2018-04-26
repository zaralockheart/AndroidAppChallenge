package com.example.ucoppp.androidappchallenge.database.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface UserDao {

    /**
     * Get a user by email.
     * @return the user from the table with a specific id.
     */
    @Query("select * from Users where email = :email")
    fun getUserByEmail(email: String): Flowable<User>

    /**
     * Just in case if you wanna delete all users
     */
    @Query("delete from users")
    fun deleteAllUser()
}