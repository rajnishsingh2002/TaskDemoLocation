package com.simple.taskdemoapp.mapfragment.model

data class StatusModel(
    val id: String,
    val currentDate: String,
    val currentTime: String,
    val location: String,
    val workStatus: String,
    val latitude: Double,
    val longitude: Double
)