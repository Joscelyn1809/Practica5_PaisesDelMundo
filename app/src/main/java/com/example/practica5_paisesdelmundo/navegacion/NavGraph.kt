package com.example.practica5_paisesdelmundo.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.practica5_paisesdelmundo.data.room.CityState
import com.example.practica5_paisesdelmundo.data.room.CountryState
import com.example.practica5_paisesdelmundo.data.room.GpsState
import com.example.practica5_paisesdelmundo.data.room.LanguageState
import com.example.practica5_paisesdelmundo.data.room.TPState
import com.example.practica5_paisesdelmundo.data.room.events.CityEvent
import com.example.practica5_paisesdelmundo.data.room.events.CountryEvent
import com.example.practica5_paisesdelmundo.data.room.events.GpsEvent
import com.example.practica5_paisesdelmundo.data.room.events.LanguageEvent
import com.example.practica5_paisesdelmundo.data.room.events.TpEvent
import com.example.practica5_paisesdelmundo.ui.AddCityScreen
import com.example.practica5_paisesdelmundo.ui.AddCountryScreen
import com.example.practica5_paisesdelmundo.ui.AddLanguageScreen
import com.example.practica5_paisesdelmundo.ui.AddTouristicPointScreen
import com.example.practica5_paisesdelmundo.ui.CountriesScreen
import com.example.practica5_paisesdelmundo.ui.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    state: CountryState,
    languageState: LanguageState,
    onEvent: (CountryEvent) -> Unit,
    onLanguageEvent: (LanguageEvent) -> Unit,
    cityState: CityState,
    onCityEvent: (CityEvent) -> Unit,
    tpState: TPState,
    onTpEvent: (TpEvent) -> Unit,
    gpsState: GpsState,
    onGpsEvent: (GpsEvent) -> Unit

) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.Countries.route
        ) {
            CountriesScreen(navController, state, onEvent)
        }

        composable(
            route = Screen.AddCountry.route
        ) {
            AddCountryScreen(navController, state, onEvent)
        }

        composable(
            route = Screen.AddLanguage.route
        ) {
            AddLanguageScreen(navController, languageState, onLanguageEvent)
        }

        composable(
            route = Screen.AddCity.route
        ) {
            AddCityScreen(navController, cityState, onCityEvent)
        }

        composable(
            route = Screen.AddTouristicPoint.route
        ) {
            AddTouristicPointScreen(navController, tpState, onTpEvent, gpsState, onGpsEvent)
        }
    }
}