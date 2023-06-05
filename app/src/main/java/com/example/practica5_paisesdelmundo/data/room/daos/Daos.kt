package com.example.practica5_paisesdelmundo.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.practica5_paisesdelmundo.data.room.models.City
import com.example.practica5_paisesdelmundo.data.room.models.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Upsert //Añade o reemplaza según sea el caso
    suspend fun upsertCountry(country: Country)

    @Delete
    suspend fun deleteCountry(country: Country)

    @Query("SELECT * FROM COUNTRY_TABLE")
    fun getAllCountries(): Flow<List<Country>>

    @Query("SELECT * FROM COUNTRY_TABLE ORDER BY countryName ASC")
    fun getAllCountriesOrderedByName(): Flow<List<Country>>

    @Query("SELECT * FROM COUNTRY_TABLE ORDER BY countryContinent ASC")
    fun getAllCountriesOrderedByContinent(): Flow<List<Country>>

    @Query("SELECT * FROM COUNTRY_TABLE WHERE countryId =:id")
    fun getCountry(id: Int): Flow<Country>
}

@Dao
interface CityDao {
    @Upsert
    suspend fun upsertCity(city: City)

    @Delete
    suspend fun deleteCity(city: City)

    @Query("SELECT * FROM CITY_TABLE")
    fun getAllCities(): Flow<List<City>>

    @Query("SELECT * FROM CITY_TABLE ORDER BY cityName ASC")
    fun getAllCitiesOrderedByName(): Flow<List<City>>

    @Query("SELECT * FROM CITY_TABLE ORDER BY cityPopulation ASC")
    fun getAllCitiesOrderedByPopulation(): Flow<List<City>>

    @Query("SELECT * FROM CITY_TABLE WHERE cityId =:id")
    fun getCity(id: Int): Flow<City>
}