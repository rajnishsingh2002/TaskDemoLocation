package com.simple.taskdemoapp.mapfragment.repository

class StatusRepository {
    fun getStatus() = RetrofitInstance.api.getAllStatus()
}