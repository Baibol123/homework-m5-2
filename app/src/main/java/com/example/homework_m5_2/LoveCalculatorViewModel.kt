package com.example.homework_m5_2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_m5_2.remote.LoveModel

class LoveCalculatorViewModel : ViewModel() {

    private val repository = Repository()
    fun getPercentage(firstName: String, secondName: String) {
        repository.getPercentage(firstName, secondName)
    }
}