package com.example.practica5_paisesdelmundo.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.practica5_paisesdelmundo.data.room.models.City
import com.example.practica5_paisesdelmundo.data.room.models.Country
import com.example.practica5_paisesdelmundo.data.room.models.CountryLanguage
import com.example.practica5_paisesdelmundo.data.room.models.PositionGps
import com.example.practica5_paisesdelmundo.data.room.models.TouristicPoint
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
interface CountryLanguageDao {
    @Upsert
    suspend fun upsertLanguage(countryLanguage: CountryLanguage)

    @Delete
    suspend fun deleteLanguage(countryLanguage: CountryLanguage)

    @Query("SELECT * FROM LANGUAGE_TABLE ORDER BY language ASC")
    fun getAllLanguagesOrderedByName(): Flow<List<CountryLanguage>>

    @Query("SELECT * FROM LANGUAGE_TABLE")
    fun getAllLanguages(): Flow<List<CountryLanguage>>

    @Query("SELECT * FROM LANGUAGE_TABLE WHERE languageId =:id")
    fun getLanguage(id: Int): Flow<CountryLanguage>
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

@Dao
interface TouristicPointDao {
    @Upsert
    suspend fun upsertTp(point: TouristicPoint)

    @Delete
    suspend fun deleteTp(point: TouristicPoint)

    @Query("SELECT * FROM TOURISTIC_POINT_TABLE ORDER BY touristicName ASC")
    fun getAllPointsOrderedByName(): Flow<List<TouristicPoint>>

    @Query("SELECT * FROM TOURISTIC_POINT_TABLE ORDER BY fee ASC")
    fun getAllPointsOrderedByFee(): Flow<List<TouristicPoint>>

    @Query("SELECT * FROM TOURISTIC_POINT_TABLE")
    fun getAllPoints(): Flow<List<TouristicPoint>>

    @Query("SELECT * FROM TOURISTIC_POINT_TABLE WHERE pointId =:id")
    fun getPoint(id: Int): Flow<TouristicPoint>
}


@Dao
interface GpsDao {
    @Upsert
    suspend fun upsertGps(gps: PositionGps)

    @Delete
    suspend fun deleteGps(gps: PositionGps)

    @Query("SELECT * FROM POSITION_GPS_TABLE")
    fun getAllPositions(): Flow<List<PositionGps>>

    @Query("SELECT * FROM POSITION_GPS_TABLE WHERE gpsId =:id")
    fun getPosition(id: Int): Flow<PositionGps>
}


/*
@Dao
interface CountryLanguageRegistryDao {
    @Upsert
    suspend fun upsertCountryLanguage(cl: CountryLanguageRegistry)

    @Delete
    suspend fun deleteCountryLanguage(cl: CountryLanguageRegistry)

    @Query("SELECT * FROM COUNTRY_TABLE WHERE countryId = (SELECT countryId FROM COUNTRY_LANGUAGE_REGISTRY WHERE languageIdFk = :languageId)")
    fun getCountriesOfLanguage(languageId: Int): Flow<List<Country>>

    @Query("SELECT * FROM LANGUAGE_TABLE WHERE languageId = (SELECT languageId FROM COUNTRY_LANGUAGE_REGISTRY WHERE countryId = :idCountry)")
    fun getLanguagesFromCountry(idCountry: Int): Flow<List<CountryLanguage>>
}
*/
