package com.example.practica5_paisesdelmundo.data.room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica5_paisesdelmundo.data.room.CityState
import com.example.practica5_paisesdelmundo.data.room.CountryState
import com.example.practica5_paisesdelmundo.data.room.daos.CityDao
import com.example.practica5_paisesdelmundo.data.room.events.CityEvent
import com.example.practica5_paisesdelmundo.data.room.events.CountryEvent
import com.example.practica5_paisesdelmundo.data.room.models.City
import com.example.practica5_paisesdelmundo.data.room.models.Country
import com.example.practica5_paisesdelmundo.data.room.sorts.CitySortType
import com.example.practica5_paisesdelmundo.data.room.sorts.CountrySortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CityViewModel(
    private val cityDao: CityDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(CitySortType.CITY_NAME)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _cities = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                CitySortType.CITY_NAME -> cityDao.getAllCitiesOrderedByName()
                CitySortType.CITY_POPULATION -> cityDao.getAllCitiesOrderedByPopulation()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(CityState())
    val state = combine(_state, _sortType, _cities) { state, sortType, cities ->
        state.copy(
            cities = cities,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CityState())

    fun onEvent(event: CityEvent) {
        when (event) {
            is CityEvent.DeleteCity -> {
                viewModelScope.launch {
                    cityDao.deleteCity(event.City)
                }
            }

            CityEvent.SaveCity -> {
                val cityName = state.value.cityName
                val cityDistrict = state.value.disctrictName
                val population = state.value.population

                if (cityName.isBlank() || cityDistrict.isBlank() || population.isBlank()) {
                    return
                }
                val city = City(
                    cityName = cityName,
                    cityDistrict = cityDistrict,
                    cityPopulation = population,
                    countryIdFk = 1
                )
                viewModelScope.launch {
                    cityDao.upsertCity(city)
                    _state.update {
                        it.copy(
                            cityName = "",
                            disctrictName = "",
                            population = ""
                        )
                    }
                }
            }

            is CityEvent.SetCityName -> {
                _state.update {
                    it.copy(
                        cityName = event.cityName
                    )
                }
            }

            is CityEvent.SetCityDistrict -> {
                _state.update {
                    it.copy(
                        disctrictName = event.cityDistrict
                    )
                }
            }

            is CityEvent.SetCityPopulation -> {
                _state.update {
                    it.copy(
                        population = event.cityPopulation
                    )
                }
            }

            is CityEvent.SortCities -> {
                _sortType.value = event.sortCityType
            }
        }
    }
}