package com.example.practica5_paisesdelmundo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practica5_paisesdelmundo.data.room.daos.CityDao
import com.example.practica5_paisesdelmundo.data.room.daos.CountryDao
import com.example.practica5_paisesdelmundo.data.room.models.City
import com.example.practica5_paisesdelmundo.data.room.models.Country

@Database(
    entities = [Country::class, City::class],
    version = 1,
    exportSchema = false
)
abstract class CountryDB : RoomDatabase() {
    abstract val countryDao: CountryDao
    abstract val cityDao: CityDao
}