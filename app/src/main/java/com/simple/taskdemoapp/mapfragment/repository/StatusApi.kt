package com.simple.taskdemoapp.mapfragment.repository

import com.simple.taskdemoapp.mapfragment.model.StatusModel
import retrofit2.Call
import retrofit2.http.GET

interface StatusApi {
    @GET("locationsdata") // If your endpoint is /api/v1/status
    fun getAllStatus(): Call<List<StatusModel>>
}