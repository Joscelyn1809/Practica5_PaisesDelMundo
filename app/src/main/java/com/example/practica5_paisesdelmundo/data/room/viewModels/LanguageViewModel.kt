package com.example.practica5_paisesdelmundo.data.room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica5_paisesdelmundo.data.room.LanguageState
import com.example.practica5_paisesdelmundo.data.room.daos.CountryLanguageDao
import com.example.practica5_paisesdelmundo.data.room.events.LanguageEvent
import com.example.practica5_paisesdelmundo.data.room.models.CountryLanguage
import com.example.practica5_paisesdelmundo.data.room.sorts.LanguageSortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val languageDao: CountryLanguageDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(LanguageSortType.LANGUAGE_NAME)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _languages = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                LanguageSortType.LANGUAGE_NAME -> languageDao.getAllLanguagesOrderedByName()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(LanguageState())

    val languageState = combine(_state, _sortType, _languages) { state, sortType, languages ->
        state.copy(
            languages = languages,
            sortType = sortType

        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LanguageState())

    fun onEvent(event: LanguageEvent) {
        when (event) {
            is LanguageEvent.DeleteLanguage -> {
                viewModelScope.launch {
                    languageDao.deleteLanguage(event.language)
                }
            }

            LanguageEvent.SaveLanguage -> {
                val languageName = languageState.value.languageName
                val isOfficial = languageState.value.isOfficial
                val percentage = languageState.value.percentage

                if (languageName.isBlank() || isOfficial.isBlank() || percentage.isBlank()) {
                    return
                }
                val language = CountryLanguage(
                    language = languageName,
                    isOfficial = isOfficial,
                    percentage = percentage,
                    countryId = 1
                )
                viewModelScope.launch {
                    languageDao.upsertLanguage(language)
                    _state.update {
                        it.copy(
                            languageName = "",
                            isOfficial = "",
                            percentage = ""
                        )
                    }
                }
            }

            is LanguageEvent.SetLanguageName -> {
                _state.update {
                    it.copy(
                        languageName = event.languageName
                    )
                }
            }

            is LanguageEvent.SetIsOfficial -> {
                _state.update {
                    it.copy(
                        isOfficial = event.isOfficial
                    )
                }
            }

            is LanguageEvent.SetPercentage -> {
                _state.update {
                    it.copy(
                        percentage = event.percentage
                    )
                }
            }

            is LanguageEvent.SortLanguages -> {
                _sortType.value = event.sortType
            }
        }
    }
}