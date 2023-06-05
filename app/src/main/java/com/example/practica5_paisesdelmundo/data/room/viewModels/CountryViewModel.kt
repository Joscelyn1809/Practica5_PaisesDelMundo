package com.example.practica5_paisesdelmundo.data.room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica5_paisesdelmundo.data.room.CountryState
import com.example.practica5_paisesdelmundo.data.room.daos.CountryDao
import com.example.practica5_paisesdelmundo.data.room.events.CountryEvent
import com.example.practica5_paisesdelmundo.data.room.models.Country
import com.example.practica5_paisesdelmundo.data.room.sorts.CountrySortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryViewModel(
    private val countryDao: CountryDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(CountrySortType.COUNTRY_NAME)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _countries = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                CountrySortType.COUNTRY_NAME -> countryDao.getAllCountriesOrderedByName()
                CountrySortType.COUNTRY_CONTINENT -> countryDao.getAllCountriesOrderedByContinent()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(CountryState())
    val state = combine(_state, _sortType, _countries) { state, sortType, countries ->
        state.copy(
            countries = countries,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CountryState())

    fun onEvent(event: CountryEvent) {
        when (event) {
            is CountryEvent.DeleteCountry -> {
                viewModelScope.launch {
                    countryDao.deleteCountry(event.country)
                }
            }

            CountryEvent.SaveCountry -> {
                val countryName = state.value.countryName
                val countryContinent = state.value.countryContinent

                if (countryName.isBlank() || countryName.isBlank()) {
                    return
                }
                val country = Country(
                    countryName = countryName,
                    countryContinent = countryContinent
                )
                viewModelScope.launch {
                    countryDao.upsertCountry(country)
                    _state.update {
                        it.copy(
                            countryName = "",
                            countryContinent = ""
                        )
                    }
                }
            }

            is CountryEvent.SetCountryName -> {
                _state.update {
                    it.copy(
                        countryName = event.countryName
                    )
                }
            }

            is CountryEvent.SetCountryContinent -> {
                _state.update {
                    it.copy(
                        countryContinent = event.countryContinent
                    )
                }
            }

            is CountryEvent.SortCountries -> {
                _sortType.value = event.sortType
            }
        }
    }
}