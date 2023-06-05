package com.example.practica5_paisesdelmundo.navegacion

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Countries : Screen(route = "countries_screen")
    object AddCountry : Screen(route = "add_country_screen")
    object AddCity : Screen(route = "add_city_screen")

}
