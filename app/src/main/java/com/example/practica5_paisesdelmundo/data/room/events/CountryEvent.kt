package com.example.practica5_paisesdelmundo.data.room.events

import com.example.practica5_paisesdelmundo.data.room.models.Country
import com.example.practica5_paisesdelmundo.data.room.sorts.CountrySortType

sealed interface CountryEvent {
    object SaveCountry : CountryEvent
    data class SetCountryName(val countryName: String) : CountryEvent
    data class SetCountryContinent(val countryContinent: String) : CountryEvent
    data class SortCountries(val sortType: CountrySortType) : CountryEvent
    data class DeleteCountry(val country: Country) : CountryEvent
    //data class GetLanguagesOfCountry(val countryId: Int) : CountryEvent
}