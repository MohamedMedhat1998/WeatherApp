package com.andalus.mvpwithstrategy.networking

import com.andalus.mvpwithstrategy.entities.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getByCity(@Query("q") city: String): Call<WeatherResponse>

}