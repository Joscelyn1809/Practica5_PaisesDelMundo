package com.example.practica5_paisesdelmundo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.practica5_paisesdelmundo.data.room.CountryDB
import com.example.practica5_paisesdelmundo.data.room.viewModels.CityViewModel
import com.example.practica5_paisesdelmundo.data.room.viewModels.CountryViewModel
import com.example.practica5_paisesdelmundo.data.room.viewModels.GpsViewModel
import com.example.practica5_paisesdelmundo.data.room.viewModels.LanguageViewModel
import com.example.practica5_paisesdelmundo.data.room.viewModels.TpViewModel
import com.example.practica5_paisesdelmundo.navegacion.SetupNavGraph
import com.example.practica5_paisesdelmundo.ui.theme.Practica5_PaisesDelMundoTheme


class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CountryDB::class.java,
            "countries.db"
        ).build()
    }

    private val viewModel by viewModels<CountryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CountryViewModel(db.countryDao) as T
                }
            }
        }
    )

    private val languageViewModel by viewModels<LanguageViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LanguageViewModel(db.languageDao) as T
                }
            }
        }
    )

    private val cityViewModel by viewModels<CityViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CityViewModel(db.cityDao) as T
                }
            }
        }
    )

    private val tpViewModel by viewModels<TpViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TpViewModel(db.tpDao) as T
                }
            }
        }
    )

    private val gpsViewModel by viewModels<GpsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return GpsViewModel(db.gpsDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practica5_PaisesDelMundoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    var navController = rememberNavController()
                    val state by viewModel.state.collectAsState()
                    val languageState by languageViewModel.languageState.collectAsState()
                    val cityState by cityViewModel.state.collectAsState()
                    val tpState by tpViewModel.tpState.collectAsState()
                    val gpsState by gpsViewModel.gpsState.collectAsState()

                    SetupNavGraph(
                        navController = navController,
                        state = state,
                        languageState = languageState,
                        onEvent = viewModel::onEvent,
                        onLanguageEvent = languageViewModel::onEvent,
                        cityState = cityState,
                        onCityEvent = cityViewModel::onEvent,
                        tpState = tpState,
                        onTpEvent = tpViewModel::onEvent,
                        gpsState = gpsState,
                        onGpsEvent = gpsViewModel::onEvent
                    )
                }
            }
        }
    }
}
