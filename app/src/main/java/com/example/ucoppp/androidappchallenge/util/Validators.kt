package com.example.ucoppp.androidappchallenge.util

/**
 * On invalid password format, login click should show error.
 * “Password should contain
 * one special character
 * and a
 * minimum 8 characters required”
 */
fun String.isValidPassword() = Regex("^(?=(.*[\\W]))(?!.*\\s).{8,}").containsMatchIn(this)

/**
 * On invalid email format, login click should show error. “Email is not valid”
 * To investigate why android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches(); return NPE
 */
fun String.isValidEmail() = Regex("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+").containsMatchIn(this)

/**
 * Tbh, I am not sure about Malaysian mobile number, so I'm going to put default as min 8 max 12. Feel free to change
 */
fun String.validateMobileNumber(min:Int = 8, max: Int = 12) = Regex("^\\+[0-9]{$min,$max}$").containsMatchIn(this)