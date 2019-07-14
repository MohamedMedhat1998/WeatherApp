package com.andalus.mvpwithstrategy.api_strategy

import android.os.AsyncTask
import com.andalus.mvpwithstrategy.api_callback_strategy.ApiCallback
import com.andalus.mvpwithstrategy.data.WeatherDao
import com.andalus.mvpwithstrategy.data.WeatherEntity
import com.andalus.mvpwithstrategy.utils.Constants.Companion.NOT_FOUND

class LocalDataApi(
    override var callback: ApiCallback<WeatherEntity>,
    private val dao: WeatherDao,
    private val name: String
) : Api<WeatherEntity> {

    override fun fetchData() {
        val asyncTask = LocalAsyncTask(callback, dao, name)
        asyncTask.execute()
    }

    private class LocalAsyncTask(val callback: ApiCallback<WeatherEntity>, val dao: WeatherDao, val name: String) :
        AsyncTask<Void, Void, List<WeatherEntity>>() {

        override fun doInBackground(vararg params: Void?): List<WeatherEntity>{
            return dao.getByName(name)
        }

        override fun onPostExecute(result: List<WeatherEntity>?) {
            super.onPostExecute(result)
            if (!result.isNullOrEmpty())
                callback.onDataLoaded(result[0])
            else
                callback.onError(NOT_FOUND)
        }

    }

}