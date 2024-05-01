package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.Screens

@Composable
fun Menu(navController: NavController){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {navController.navigate(route= Screens.Login.route)}) {
            Text(text = "Ejercicio Presupuesto")
        }
        Button(onClick = {navController.navigate(route= Screens.Login.route)}) {
            Text(text = "PantallaLogin")
        }

    }
}