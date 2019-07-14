package com.andalus.mvpwithstrategy.api_strategy

import com.andalus.mvpwithstrategy.api_callback_strategy.ApiCallback

interface Api<T> {

    var callback: ApiCallback<T>

    fun fetchData()

}