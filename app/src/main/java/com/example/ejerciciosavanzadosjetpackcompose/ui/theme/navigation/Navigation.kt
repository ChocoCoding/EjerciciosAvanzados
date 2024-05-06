package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Ejercicio1ViewModel
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Menu
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Producto
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.App
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.AppAgenda
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.AppCarrito
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.AppLogin


import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.Screens

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val viewModel = viewModel<Ejercicio1ViewModel>()
    NavHost(navController, startDestination = Screens.Menu.route){
        composable(route=Screens.Menu.route){ Menu(navController = navController) }
        composable(route= Screens.Login.route){
            AppLogin(navController = navController) //Nombre de la funcion a ejecutar
        }
        composable(route= Screens.Ejercicio1.route){
            App(navController = navController,viewModel = viewModel) //Nombre de la funcion a ejecutar
        }
        composable(route= Screens.Ejercicio2.route){
            AppAgenda()//Nombre de la funcion a ejecutar
        }
        composable(route = Screens.Carrito.route){
            AppCarrito(navController = navController,
                viewModel = viewModel
            )
        }
    }

}