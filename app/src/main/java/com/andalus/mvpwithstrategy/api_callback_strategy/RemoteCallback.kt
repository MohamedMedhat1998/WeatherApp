package com.andalus.mvpwithstrategy.api_callback_strategy

import com.andalus.mvpwithstrategy.entities.WeatherResponse

class RemoteCallback : ApiCallback<WeatherResponse> {

    override fun onDataLoaded(data: WeatherResponse) {

    }

    override fun onError(error: String) {

    }

}