package com.example.homework_m5_2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.homework_m5_2.remote.LoveModel
import com.example.homework_m5_2.remote.RetrofitService
class Repository  {
    val api = RetrofitService().api

    private val _loveData = MutableLiveData<LoveModel>()
    val loveData: MutableLiveData<LoveModel> get() = _loveData

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    fun getPercentage(firstName: String, secondName: String) {
        RetrofitService().api.getPercentage(firstName, secondName)
            .enqueue(object : Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d("123", "onResponse: $it")
                            _loveData.postValue(it)
                        }
                    }
                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    Log.d("123", "onFailure: " + t.message)
                    _error.postValue(t.message)
                }
            })
    }
}
