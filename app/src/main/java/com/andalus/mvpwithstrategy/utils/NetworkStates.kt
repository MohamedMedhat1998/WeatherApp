package com.andalus.mvpwithstrategy.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkStates(private val context: Context) {

    fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null
    }

}