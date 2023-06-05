package com.example.practica5_paisesdelmundo.data.room.events

import com.example.practica5_paisesdelmundo.data.room.models.CountryLanguage
import com.example.practica5_paisesdelmundo.data.room.sorts.LanguageSortType

sealed interface LanguageEvent {
    object SaveLanguage : LanguageEvent
    data class SetLanguageName(val languageName: String) : LanguageEvent
    data class SetIsOfficial(val isOfficial: String) : LanguageEvent
    data class SetPercentage(val percentage: String) : LanguageEvent
    data class DeleteLanguage(val language: CountryLanguage) : LanguageEvent
    data class SortLanguages(val sortType: LanguageSortType) : LanguageEvent
    //data class GetCountriesWithLanguage(val languageId: Int) : LanguageEvent
}