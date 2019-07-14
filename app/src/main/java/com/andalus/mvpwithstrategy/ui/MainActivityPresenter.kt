package com.andalus.mvpwithstrategy.ui

import com.andalus.mvpwithstrategy.data.WeatherEntity
import com.andalus.mvpwithstrategy.model.WeatherModel

class MainActivityPresenter(private val view: MainActivityContract.View) :
    MainActivityContract.Presenter {

    lateinit var model: WeatherModel

    override fun setData(data: WeatherEntity) {
        view.displayData(data)
        view.hideIndicator()
        view.showData()
    }

    override fun setError(error: String) {
        view.showMessage(error)
        view.hideIndicator()
        view.hideData()
    }

    override fun onSearchButtonClicked(city: String) {
        model.start(city)
        view.hideData()
        view.showIndicator()
    }
}