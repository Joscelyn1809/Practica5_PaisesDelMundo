package com.example.practica5_paisesdelmundo.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.practica5_paisesdelmundo.ui.AddCityScreen
import com.example.practica5_paisesdelmundo.ui.AddCountryScreen
import com.example.practica5_paisesdelmundo.ui.CountriesScreen
import com.example.practica5_paisesdelmundo.ui.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
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
            CountriesScreen(navController)
        }

        composable(
            route = Screen.AddCountry.route
        ) {
            AddCountryScreen(navController)
        }

        composable(
            route = Screen.AddCity.route
        ) {
            AddCityScreen(navController)
        }
    }
}