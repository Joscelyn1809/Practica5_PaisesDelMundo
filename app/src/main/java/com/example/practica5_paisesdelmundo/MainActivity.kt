package com.example.practica5_paisesdelmundo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.practica5_paisesdelmundo.navegacion.SetupNavGraph
import com.example.practica5_paisesdelmundo.ui.theme.Practica5_PaisesDelMundoTheme


class MainActivity : ComponentActivity() {
    //lateinit var navController: NavHostController
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
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}
