package com.andalus.mvpwithstrategy.ui

import com.andalus.mvpwithstrategy.BasePresenter
import com.andalus.mvpwithstrategy.data.WeatherEntity

interface MainActivityContract {

    interface Presenter : BasePresenter<WeatherEntity> {
        fun onSearchButtonClicked(city: String)
    }

    interface View {
        fun showMessage(text: String)
        fun displayData(data: WeatherEntity)
        fun getCityName(): String
        fun hideIndicator()
        fun showIndicator()
        fun hideData()
        fun showData()
    }
}