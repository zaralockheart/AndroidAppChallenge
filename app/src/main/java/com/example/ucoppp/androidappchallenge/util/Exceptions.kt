package com.example.ucoppp.androidappchallenge.util

fun emptyDataException(error: String): Exception = Exception(error)

class EmptyPrimaryFieldsException constructor(message: String) : Exception(message)