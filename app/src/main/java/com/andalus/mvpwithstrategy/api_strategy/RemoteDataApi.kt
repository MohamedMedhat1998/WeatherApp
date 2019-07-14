package com.andalus.mvpwithstrategy.api_strategy

import com.andalus.mvpwithstrategy.api_callback_strategy.ApiCallback
import com.andalus.mvpwithstrategy.data.WeatherEntity
import com.andalus.mvpwithstrategy.entities.WeatherResponse
import com.andalus.mvpwithstrategy.networking.AuthorizationInterceptor
import com.andalus.mvpwithstrategy.networking.WeatherService
import com.andalus.mvpwithstrategy.utils.Constants.Companion.BASE_URL
import com.andalus.mvpwithstrategy.utils.Constants.Companion.NOT_FOUND
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataApi(override var callback: ApiCallback<WeatherEntity>, val name: String) : Api<WeatherEntity> {

    override fun fetchData() {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthorizationInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)

        val call = service.getByCity(name)

        call.enqueue(object : Callback<WeatherResponse> {

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback.onError(t.message!!)
            }

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                val gson = GsonBuilder().create()
                val weatherResponse = gson.fromJson(response.body().toString(), WeatherResponse::class.java)
                if (weatherResponse == null) {
                    callback.onError(NOT_FOUND)
                } else {
                    val weatherEntity =
                        WeatherEntity(
                            weatherResponse.id,
                            weatherResponse.name,
                            weatherResponse.sys.country,
                            weatherResponse.weather[0].description,
                            weatherResponse.main.temp.toFloat(),
                            "http://openweathermap.org/img/w/${weatherResponse.weather[0].icon}.png"
                        )
                    callback.onDataLoaded(weatherEntity)
                }
            }
        })
    }
}