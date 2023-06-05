package com.example.practica5_paisesdelmundo.data.room

import com.example.practica5_paisesdelmundo.data.room.models.City
import com.example.practica5_paisesdelmundo.data.room.models.Country
import com.example.practica5_paisesdelmundo.data.room.models.CountryLanguage
import com.example.practica5_paisesdelmundo.data.room.models.PositionGps
import com.example.practica5_paisesdelmundo.data.room.models.TouristicPoint
import com.example.practica5_paisesdelmundo.data.room.sorts.CitySortType
import com.example.practica5_paisesdelmundo.data.room.sorts.CountrySortType
import com.example.practica5_paisesdelmundo.data.room.sorts.LanguageSortType
import com.example.practica5_paisesdelmundo.data.room.sorts.TpSortType

data class CountryState(
    val countries: List<Country> = emptyList(),
    val countryName: String = "",
    val countryContinent: String = "",
    val sortType: CountrySortType = CountrySortType.COUNTRY_NAME
)

data class LanguageState(
    val languages: List<CountryLanguage> = emptyList(),
    val languageName: String = "",
    val isOfficial: String = "",
    val percentage: String = "0",
    val sortType: LanguageSortType = LanguageSortType.LANGUAGE_NAME
)

data class CityState(
    val cities: List<City> = emptyList(),
    val cityName: String = "",
    val disctrictName: String = "",
    val population: String = "",
    val sortType: CitySortType = CitySortType.CITY_NAME
)

data class TPState(
    val points: List<TouristicPoint> = emptyList(),
    val touristicName: String = "",
    val description: String = "",
    val fee: String = "",
    val tpSortType: TpSortType = TpSortType.TOURISTIC_NAME
)

data class GpsState(
    val positions: List<PositionGps> = emptyList(),
    val position: String = ""
)