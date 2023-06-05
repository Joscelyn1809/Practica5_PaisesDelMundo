package com.example.practica5_paisesdelmundo.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica5_paisesdelmundo.R
import com.example.practica5_paisesdelmundo.navegacion.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { CountryAppBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddCountry.route) },
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
                CountryContent(navController)
            }

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Países", color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }, //modifier = Modifier.padding(start = 20.dp)
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

@Composable
fun CountryContent(
    navController: NavController
) {

    var list = listOf<String>("Mexico", "Estados Unidos", "Colombia", "Alaska")

    Box(
        modifier = Modifier.fillMaxSize(),
        //contentAlignment = Alignment.TopCenter,

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            Text(
                text = "Países del Mundo",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(list) { country ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                            .background(MaterialTheme.colorScheme.surface),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = country,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                        IconButton(onClick = {
                            //onEvent(CountryEvent.DeleteCountry(country))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete country"
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 *  Box(
modifier = Modifier
.fillMaxSize()
.padding(top = 5.dp),
contentAlignment = Alignment.TopCenter
//horizontalAlignment = Alignment.CenterHorizontally,
//verticalArrangement = Arrangement.Center
) {
Text(
text = "Países",
fontSize = 50.sp,
fontWeight = FontWeight.ExtraBold,
textAlign = TextAlign.Center,
color = MaterialTheme.colorScheme.onSurface,
//modifier = Modifier.padding(vertical = 10.dp)
)
/*
LazyColumn(
modifier = Modifier
.fillMaxWidth(),
horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.SpaceEvenly

) {

Button(
onClick = {
navController.navigate(route = Screen.AddCity.route)
}
) {
Text(text = "Puntos Turísticos", fontSize = 25.sp)
}
}*/
 */