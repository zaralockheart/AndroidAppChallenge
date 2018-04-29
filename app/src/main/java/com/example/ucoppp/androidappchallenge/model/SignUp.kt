package com.example.ucoppp.androidappchallenge.model

data class SignUp(
        val email: String,
        val password: String,
        val firstName: String?,
        val lastName: String?,
        val mobileNumber: String,
        val gender: String
)