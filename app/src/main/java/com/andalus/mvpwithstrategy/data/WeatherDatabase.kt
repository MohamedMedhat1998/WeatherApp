package com.andalus.mvpwithstrategy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andalus.mvpwithstrategy.utils.Constants.Companion.DATABASE_NAME

@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        var instance: WeatherDatabase? = null
        fun getInstance(context: Context): WeatherDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context, WeatherDatabase::class.java, DATABASE_NAME).build()
            return instance!!
        }
    }

    abstract fun getDao(): WeatherDao

}