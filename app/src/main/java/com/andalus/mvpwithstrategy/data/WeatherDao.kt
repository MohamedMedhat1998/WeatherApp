package com.andalus.mvpwithstrategy.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeatherDao {

    @Insert
    fun insert(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weatherEntity")
    fun getAll(): List<WeatherEntity>

    @Query("SELECT * FROM weatherEntity WHERE LOWER(city) = LOWER(:name)")
    fun getByName(name: String): List<WeatherEntity>

    @Query("SELECT * FROM weatherEntity WHERE id = :id")
    fun getById(id: Long): List<WeatherEntity>

    @Update
    fun updateWeather(weatherEntity: WeatherEntity)

}