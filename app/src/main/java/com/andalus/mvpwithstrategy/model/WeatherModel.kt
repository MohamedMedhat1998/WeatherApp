package com.andalus.mvpwithstrategy.model

import android.os.AsyncTask
import com.andalus.mvpwithstrategy.BasePresenter
import com.andalus.mvpwithstrategy.api_callback_strategy.ApiCallback
import com.andalus.mvpwithstrategy.api_strategy.Api
import com.andalus.mvpwithstrategy.api_strategy.LocalDataApi
import com.andalus.mvpwithstrategy.api_strategy.RemoteDataApi
import com.andalus.mvpwithstrategy.data.WeatherDao
import com.andalus.mvpwithstrategy.data.WeatherEntity
import com.andalus.mvpwithstrategy.utils.NetworkStates

private const val LOCAL = "local"
private const val REMOTE = "remote"

class WeatherModel(
    private val networkStates: NetworkStates,
    private val dao: WeatherDao,
    private val presenter: BasePresenter<WeatherEntity>
) :
    ApiCallback<WeatherEntity> {

    private var flag = REMOTE

    lateinit var api: Api<WeatherEntity>

    fun start(city: String) {
        if (networkStates.isConnected()) {
            flag = REMOTE
            api = RemoteDataApi(this, city)
        } else {
            flag = LOCAL
            api = LocalDataApi(this, dao, city)
        }
        api.fetchData()
    }

    override fun onDataLoaded(data: WeatherEntity) {
        if (flag == REMOTE) {
            DatabaseAsyncTask(dao, data).execute()
        }
        presenter.setData(data)
    }

    override fun onError(error: String) {
        presenter.setError(error)
    }

    private class DatabaseAsyncTask(val dao: WeatherDao, val data: WeatherEntity) : AsyncTask<Void, Void, Unit>() {
        override fun doInBackground(vararg params: Void?) {
            if (dao.getById(data.id).isEmpty())
                dao.insert(data)
            else
                dao.updateWeather(data)
        }
    }

}