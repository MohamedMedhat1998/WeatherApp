package com.andalus.mvpwithstrategy.utils

class Constants {

    companion object {
        const val APP_ID = "appid"
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val DATABASE_NAME = "weather-database"

        const val NOT_FOUND = "city not found"

        class Columns {
            companion object {
                const val CITY = "city"
                const val COUNTRY = "country"
                const val TEMPERATURE = "temperature"
                const val DESCRIPTION = "description"
                const val ICON = "icon"
            }
        }
    }

}