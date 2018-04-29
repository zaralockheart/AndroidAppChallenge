package com.example.ucoppp.androidappchallenge.database.user

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.util.*


// First let's create our table
// Call it Users
@Entity(tableName = "Users",
        indices=[Index(value = ["email"], unique = true)])
data class User(
        @PrimaryKey
        @ColumnInfo(name = "user_id")
        val userID: String = UUID.randomUUID().toString(),
        @NonNull
        @ColumnInfo(name = "email")
        val email: String,
        @NonNull
        @ColumnInfo(name = "password")
        val password: String,
        @ColumnInfo(name = "first_name")
        val firstName: String,
        @ColumnInfo(name = "last_name")
        val lastName: String,
        @NonNull
        @ColumnInfo(name = "mobile_number")
        val mobileNumber: String,
        @NonNull
        @ColumnInfo(name = "gender")
        val gender: String
)