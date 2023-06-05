package com.example.practica5_paisesdelmundo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practica5_paisesdelmundo.data.room.daos.CityDao
import com.example.practica5_paisesdelmundo.data.room.daos.CountryDao
import com.example.practica5_paisesdelmundo.data.room.daos.CountryLanguageDao
import com.example.practica5_paisesdelmundo.data.room.daos.GpsDao
import com.example.practica5_paisesdelmundo.data.room.daos.TouristicPointDao
import com.example.practica5_paisesdelmundo.data.room.models.City
import com.example.practica5_paisesdelmundo.data.room.models.Country
import com.example.practica5_paisesdelmundo.data.room.models.CountryLanguage
import com.example.practica5_paisesdelmundo.data.room.models.PositionGps
import com.example.practica5_paisesdelmundo.data.room.models.TouristicPoint

@Database(
    entities = [
        Country::class,
        City::class,
        CountryLanguage::class,
        TouristicPoint::class,
        PositionGps::class
    ], // CountryLanguageRegistry::class
    version = 1,
    exportSchema = false
)
abstract class CountryDB : RoomDatabase() {
    abstract val countryDao: CountryDao
    abstract val cityDao: CityDao
    abstract val languageDao: CountryLanguageDao
    abstract val tpDao: TouristicPointDao
    abstract val gpsDao: GpsDao

    //abstract val countryLanguageRegistryDao: CountryLanguageRegistryDao
}