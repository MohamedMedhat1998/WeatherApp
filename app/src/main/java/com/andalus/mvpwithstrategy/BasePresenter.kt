package com.andalus.mvpwithstrategy

interface BasePresenter<T> {
    fun setData(data: T)
    fun setError(error: String)
}