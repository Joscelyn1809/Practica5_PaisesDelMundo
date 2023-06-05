package com.example.practica5_paisesdelmundo.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.practica5_paisesdelmundo.data.room.LanguageState
import com.example.practica5_paisesdelmundo.data.room.events.LanguageEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLanguageScreen(
    navController: NavController,
    state: LanguageState,
    onEvent: (LanguageEvent) -> Unit
) {
    Scaffold(
        topBar = { AddLanguageAppBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(LanguageEvent.SaveLanguage)
                    navController.popBackStack()
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                shape = CircleShape,
                content = {
                    Icon(Icons.Default.Add, contentDescription = "Add Language")
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
                AddLanguageContent(navController, state, onEvent)
            }

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLanguageAppBar(navController: NavController) {
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
fun AddLanguageContent(
    navController: NavController,
    state: LanguageState,
    onEvent: (LanguageEvent) -> Unit
) {

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
                text = "Añadiendo lenguaje",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {

                TextField(
                    value = state.languageName,
                    onValueChange = {
                        onEvent(LanguageEvent.SetLanguageName(it))
                        /*countryNameState.value = it*/
                    },
                    placeholder = {
                        Text(text = "Nombre")
                    },
                    shape = CircleShape,
                    modifier = Modifier.wrapContentWidth()
                )

                TextField(
                    value = state.isOfficial,
                    onValueChange = {
                        onEvent(LanguageEvent.SetIsOfficial(it))
                        /*continentNameState.value = it*/
                    },
                    placeholder = {
                        Text(text = "¿Es oficial?")
                    },
                    shape = CircleShape,
                    modifier = Modifier.wrapContentWidth()
                )

                TextField(
                    value = state.percentage,
                    onValueChange = {
                        onEvent(LanguageEvent.SetPercentage(it))
                        /*continentNameState.value = it*/
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = "Porcentaje")
                    },
                    shape = CircleShape,
                    modifier = Modifier.wrapContentWidth()
                )
            }
        }
    }
}