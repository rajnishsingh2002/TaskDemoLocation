package com.simple.taskdemoapp
import retrofit2.http.GET
import retrofit2.Call

interface StatusApi {
    @GET("locationsdata") // If your endpoint is /api/v1/status
    fun getAllStatus(): Call<List<StatusModel>>
}
