package com.andalus.mvpwithstrategy.api_callback_strategy

interface ApiCallback<T> {

    fun onDataLoaded(data: T)

    fun onError(error: String)

}