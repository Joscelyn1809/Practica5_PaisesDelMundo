package com.example.practica5_paisesdelmundo.data.room

import com.example.practica5_paisesdelmundo.data.room.models.Country
import com.example.practica5_paisesdelmundo.data.room.sorts.CountrySortType

data class CountryState(
    val countries: List<Country> = emptyList(),
    val countryName: String = "",
    val countryContinent: String = "",
    val sortType: CountrySortType = CountrySortType.COUNTRY_NAME
)
