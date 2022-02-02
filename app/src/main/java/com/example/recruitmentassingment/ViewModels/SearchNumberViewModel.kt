package com.example.recruitmentassingment.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class searchNumberViewModel: ViewModel() {

    val currentNumbers: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    fun setNumber(numbers: String){
        currentNumbers.value = numbers
    }
}