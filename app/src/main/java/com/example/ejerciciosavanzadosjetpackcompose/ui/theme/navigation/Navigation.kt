package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Menu
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.App

import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.PantallaPrueba2

import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.Screens

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.Menu.route){
        composable(route=Screens.Menu.route){ Menu(navController = navController) }
        composable(route= Screens.Ejercicio1.route){
            App() //Nombre de la funcion a ejecutar
        }
        composable(route= Screens.Ejercicio2.route){
            PantallaPrueba2()//Nombre de la funcion a ejecutar
        }
    }

}