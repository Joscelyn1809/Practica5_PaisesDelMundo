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
import com.example.practica5_paisesdelmundo.data.room.viewModels.CountryViewModel
import com.example.practica5_paisesdelmundo.navegacion.SetupNavGraph
import com.example.practica5_paisesdelmundo.ui.theme.Practica5_PaisesDelMundoTheme


class MainActivity : ComponentActivity() {
    //lateinit var navController: NavHostController

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
                    SetupNavGraph(
                        navController = navController,
                        state = state,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}
