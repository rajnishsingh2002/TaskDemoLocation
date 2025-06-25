package com.simple.taskdemoapp

class StatusRepository {
    fun getStatus() = RetrofitInstance.api.getAllStatus()
}
