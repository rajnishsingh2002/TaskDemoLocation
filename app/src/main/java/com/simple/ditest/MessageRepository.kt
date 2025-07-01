package com.simple.ditest

import javax.inject.Inject

class MessageRepository @Inject constructor() {
    fun getMessage(): String = "Welcome to Hilt + MVVM!"
}