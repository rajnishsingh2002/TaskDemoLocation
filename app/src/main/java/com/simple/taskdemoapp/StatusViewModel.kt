package com.simple.taskdemoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatusViewModel : ViewModel() {

    private val repository = StatusRepository()

    private val _statusList = MutableLiveData<List<StatusModel>>()
    val statusList: LiveData<List<StatusModel>> = _statusList

    fun fetchStatus() {
        repository.getStatus().enqueue(object : Callback<List<StatusModel>> {
            override fun onResponse(
                call: Call<List<StatusModel>>,
                response: Response<List<StatusModel>>
            ) {
                if (response.isSuccessful) {
                    _statusList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<StatusModel>>, t: Throwable) {
                // handle error
            }
        })
    }
}
