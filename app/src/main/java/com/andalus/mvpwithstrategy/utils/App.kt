package com.andalus.mvpwithstrategy.utils

import android.app.Application
import android.content.Context

class App : Application() {

    companion object{
        private lateinit var context: Context
        fun getContext(): Context? {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}