package com.simple.ditest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel(){

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _message.value = repository.getMessage()
    }
}