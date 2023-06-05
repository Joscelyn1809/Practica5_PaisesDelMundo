package com.example.practica5_paisesdelmundo.data.room.events

import com.example.practica5_paisesdelmundo.data.room.models.City
import com.example.practica5_paisesdelmundo.data.room.sorts.CitySortType

sealed interface CityEvent {
    object SaveCity : CityEvent
    data class SetCityName(val cityName: String) : CityEvent
    data class SetCityDistrict(val cityDistrict: String) : CityEvent
    data class SetCityPopulation(val cityPopulation: String) : CityEvent
    data class SortCities(val sortCityType: CitySortType) : CityEvent
    data class DeleteCity(val City: City) : CityEvent
}