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

@Entity(tableName = "LANGUAGE_TABLE")
data class CountryLanguage(
    @PrimaryKey(autoGenerate = true)
    val languageId: Int = 0,
    val language: String,
    val isOfficial: String,
    val percentage: String,
    val countryId: Int
)

@Entity(tableName = "CITY_TABLE")
data class City(
    val cityName: String,
    val cityDistrict: String,
    val cityPopulation: String,
    val countryIdFk: Int,
    @PrimaryKey(autoGenerate = true)
    val cityId: Int = 0
)

@Entity(tableName = "touristic_point_table")
data class TouristicPoint(
    @PrimaryKey(autoGenerate = true)
    var pointId: Int = 0,
    val touristicName: String,
    val touristicDescription: String,
    val fee: String,
    val cityIdFk: Int
)

@Entity(tableName = "POSITION_GPS_TABLE")
data class PositionGps(
    @PrimaryKey(autoGenerate = true)
    var gpsId: Int = 0,
    val position: String,
    val pointIdFk: Int
)

/*
@Entity(tableName = "COUNTRY_LANGUAGE_REGISTRY")
data class CountryLanguageRegistry(
    val countryIdFk: Int,
    val languageIdFk: Int
)*/
