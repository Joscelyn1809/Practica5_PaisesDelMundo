package com.example.practica5_paisesdelmundo.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica5_paisesdelmundo.R
import com.example.practica5_paisesdelmundo.data.room.CityState
import com.example.practica5_paisesdelmundo.data.room.events.CityEvent
import com.example.practica5_paisesdelmundo.navegacion.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityScreen(
    navController: NavController,
    state: CityState,
    onEvent: (CityEvent) -> Unit
) {
    Scaffold(topBar = { AddCityAppBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(CityEvent.SaveCity)
                    navController.popBackStack()
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                shape = CircleShape,
                content = {
                    Icon(Icons.Default.Add, contentDescription = "Add City")
                },
                modifier = Modifier.size(70.dp)
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    alpha = .1f,
                    contentScale = ContentScale.Crop
                )
                AddCityContent(navController, state, onEvent)
            }

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Ciudades", color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = "back",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityContent(
    navController: NavController,
    state: CityState,
    onEvent: (CityEvent) -> Unit
) {
    val puntoTuristicoState = remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier.fillMaxSize(),
        //contentAlignment = Alignment.Center,

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            Text(
                text = "Añadiendo Ciudad",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {

                TextField(
                    value = state.cityName,
                    onValueChange = {
                        onEvent(CityEvent.SetCityName(it))
                        /*countryNameState.value = it*/
                    },
                    placeholder = {
                        Text(text = "Nombre")
                    },
                    shape = CircleShape,
                    modifier = Modifier.wrapContentWidth()
                )

                TextField(
                    value = state.disctrictName,
                    onValueChange = {
                        onEvent(CityEvent.SetCityDistrict(it))
                        /*continentNameState.value = it*/
                    },
                    placeholder = {
                        Text(text = "Distrito")
                    },
                    shape = CircleShape,
                    modifier = Modifier.wrapContentWidth()
                )

                TextField(
                    value = state.population,
                    onValueChange = { onEvent(CityEvent.SetCityPopulation(it)) },
                    placeholder = {
                        Text(text = "Población")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = CircleShape,
                    modifier = Modifier.wrapContentWidth()
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = puntoTuristicoState.value,
                        onValueChange = { puntoTuristicoState.value = it },
                        leadingIcon = {
                            IconButton(onClick = { })
                            {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Puntos turisticos List",
                                    modifier = Modifier.size(15.dp),
                                    tint = Color.Black
                                )
                            }
                        },
                        placeholder = {
                            Text(text = "Punto Turístico")
                        },
                        shape = CircleShape,
                        modifier = Modifier.wrapContentWidth()
                    )
                    IconButton(
                        onClick = { navController.navigate(Screen.AddTouristicPoint.route) },
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 8.dp)
                            .background(
                                color = MaterialTheme.colorScheme.tertiary,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add Punto Turistico",
                            modifier = Modifier.size(15.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}