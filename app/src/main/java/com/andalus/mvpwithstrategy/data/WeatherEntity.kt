package com.andalus.mvpwithstrategy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andalus.mvpwithstrategy.utils.Constants.Companion.Columns.Companion.CITY
import com.andalus.mvpwithstrategy.utils.Constants.Companion.Columns.Companion.COUNTRY
import com.andalus.mvpwithstrategy.utils.Constants.Companion.Columns.Companion.DESCRIPTION
import com.andalus.mvpwithstrategy.utils.Constants.Companion.Columns.Companion.ICON
import com.andalus.mvpwithstrategy.utils.Constants.Companion.Columns.Companion.TEMPERATURE

@Entity
data class WeatherEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = CITY) val city: String,
    @ColumnInfo(name = COUNTRY) val country: String,
    @ColumnInfo(name = DESCRIPTION) val description: String,
    @ColumnInfo(name = TEMPERATURE) val temp: Float,
    @ColumnInfo(name = ICON) val icon: String
)