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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica5_paisesdelmundo.R
import com.example.practica5_paisesdelmundo.data.room.CountryState
import com.example.practica5_paisesdelmundo.data.room.events.CountryEvent
import com.example.practica5_paisesdelmundo.navegacion.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCountryScreen(
    navController: NavController,
    state: CountryState,
    onEvent: (CountryEvent) -> Unit
) {
    Scaffold(
        topBar = { AddCountryAppBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(CountryEvent.SaveCountry)
                    navController.popBackStack()
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                shape = CircleShape,
                content = {
                    Icon(Icons.Default.Add, contentDescription = "Add Country")
                },
                modifier = Modifier.size(70.dp)
            )
        }, content = {
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
                AddCountryContent(navController, state, onEvent)
            }

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCountryAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Añadiendo país", color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
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
fun AddCountryContent(
    navController: NavController,
    state: CountryState,
    onEvent: (CountryEvent) -> Unit
) {

    val cityNameState = remember { mutableStateOf(TextFieldValue()) }
    val languageNameState = remember { mutableStateOf(TextFieldValue()) }

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
                text = "Añadiendo país",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {

                TextField(
                    value = state.countryName,
                    onValueChange = {
                        onEvent(CountryEvent.SetCountryName(it))
                        /*countryNameState.value = it*/
                    },
                    placeholder = {
                        Text(text = "Nombre")
                    },
                    shape = CircleShape,
                    modifier = Modifier.wrapContentWidth()
                )

                TextField(
                    value = state.countryContinent,
                    onValueChange = {
                        onEvent(CountryEvent.SetCountryContinent(it))
                        /*continentNameState.value = it*/
                    },
                    placeholder = {
                        Text(text = "Continente")
                    },
                    shape = CircleShape,
                    modifier = Modifier.wrapContentWidth()
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = cityNameState.value,
                        onValueChange = { cityNameState.value = it },
                        leadingIcon = {
                            IconButton(onClick = { /*TODO*/ })
                            {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Cities List",
                                    modifier = Modifier.size(15.dp),
                                    tint = Color.Black
                                )
                            }
                        },
                        placeholder = {
                            Text(text = "Ciudad")
                        },
                        shape = CircleShape,
                        modifier = Modifier.wrapContentWidth()
                    )
                    IconButton(
                        onClick = { navController.navigate(Screen.AddCity.route) },
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
                            contentDescription = "Add Country",
                            modifier = Modifier.size(15.dp),
                            tint = Color.White
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = languageNameState.value,
                        onValueChange = { languageNameState.value = it },
                        leadingIcon = {
                            IconButton(onClick = { })
                            {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Cities List",
                                    modifier = Modifier.size(15.dp),
                                    tint = Color.Black
                                )
                            }
                        },
                        placeholder = {
                            Text(text = "Lenguaje")
                        },
                        shape = CircleShape,
                        modifier = Modifier.wrapContentWidth()
                    )
                    IconButton(
                        onClick = { navController.navigate(Screen.AddLanguage.route) },
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
                            contentDescription = "Add Country",
                            modifier = Modifier.size(15.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}