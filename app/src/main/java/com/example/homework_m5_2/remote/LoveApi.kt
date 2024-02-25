package com.example.homework_m5_2.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveApi {
    //https://love-calculator.p.rapidapi.com/getPercentage?sname=Alice&fname=John
    @GET("getPercentage")
    fun getPercentage(
        @Query("fname") firstName: String,
        @Query("sname") secondName: String,
        @Header("X-RapidAPI-Key") key: String = "a7934ca4fdmshba8140bd5aa1217p17d342jsn7c634ca5ad91",
        @Header("X-RapidAPI-Host") host: String = "love-calculator.p.rapidapi.com",

        ): Call<LoveModel>
}