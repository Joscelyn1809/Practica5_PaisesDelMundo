package com.example.practica5_paisesdelmundo.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRY_TABLE")
data class Country(
    val countryName: String,
    val countryContinent: String,
    @PrimaryKey(autoGenerate = true)
    val countryId: Int? = null,
)

@Entity(tableName = "CITY_TABLE")
data class City(
    val cityName: String,
    val cityDistrict: String,
    val cityPopulation: Int,
    val countryIdFk: Int,
    @PrimaryKey(autoGenerate = true)
    val cityId: Int = 0
)