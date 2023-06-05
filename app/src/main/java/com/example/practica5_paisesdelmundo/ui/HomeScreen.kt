package com.example.practica5_paisesdelmundo.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
fun HomeScreen(
    navController: NavController
) {
    Scaffold(topBar = { AppBar(navController) }, content = {
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
            Content(navController)
        }

    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "", color = Color.Black
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 20.dp),
                painter = painterResource(id = R.drawable.baseline_auto_awesome_mosaic_24),
                contentDescription = "Greet",
                tint = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary)
        // colorResource(id = R.color.white), elevation = 4.dp
    )
}

@Composable
fun Content(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround

        ) {
            Text(
                text = "Países \n\n del Mundo",
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Button(
                onClick = {
                    navController.navigate(route = Screen.Countries.route)
                },
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "Start", fontSize = 25.sp)
            }
        }
    }
}