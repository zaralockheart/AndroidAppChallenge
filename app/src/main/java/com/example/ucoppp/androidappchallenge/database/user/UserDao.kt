package com.example.ucoppp.androidappchallenge.database.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface UserDao {

    /**
     * Get a user by email.
     * @return the user from the table with a specific id.
     */
    @Query("select * from Users where email = :email")
    fun getUserByEmail(email: String): User

    /**
     * Just in case if you wanna delete all users
     */
    @Query("delete from users")
    fun deleteAllUser()

    /**
     * Insert a meal into db, if exists, replace
     *
     * @param meal - meal to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertUser(user: User)
}